package com.kenyajug.encounter.patient;
import com.kenyajug.encounter.core.DateTimeUtils;
import com.kenyajug.encounter.core.EncounterDatabaseManager;
import com.kenyajug.encounter.core.EncounterRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
@Repository
public class PatientRepository implements EncounterRepository<PatientDTOs.Patient,String> {
    private final EncounterDatabaseManager databaseManager;
    public PatientRepository(EncounterDatabaseManager encounterDatabaseManager){
        databaseManager = encounterDatabaseManager;
    }
    @Override
    public boolean save(PatientDTOs.Patient entity) {
        if(!(databaseManager.getConnection() instanceof Connection connection))
            return false;
        try {
            var savePatientQuery = """
                INSERT INTO Patient VALUES(?,?,?);
                """;
            var patientDob = DateTimeUtils.localDateToString(entity.dob());
            var statement = connection.prepareStatement(savePatientQuery);
            statement.setString(1,entity.uuid());
            statement.setString(2,entity.name());
            statement.setString(3, patientDob);
            return statement.executeUpdate() == 1;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteById(String uuid) {
        if(!(databaseManager.getConnection() instanceof Connection connection)) {
            return false;
        }
        try {
            var sql = """
                DELETE FROM Patient
                WHERE uuid = ?;
                """;
            var statement = connection.prepareStatement(sql);
            statement.setString(1,uuid);
            return statement.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.err.println("Failed to delete patient by uuid " + uuid + " cause " + ex.getMessage());
        }
        return false;
    }
    @Override
    public Optional<PatientDTOs.Patient> findById(String uuid) {
        if(!(databaseManager.getConnection() instanceof Connection connection)) {
            return Optional.empty();
        }
        try {
            var sql = """
                SELECT * FROM Patient
                WHERE uuid = ?;
                """;
            var statement = connection.prepareStatement(sql);
            statement.setString(1,uuid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                var patientUuid = resultSet.getString(1);
                var patientName = resultSet.getString(2);
                var dobText = resultSet.getString(3);
                var dob = DateTimeUtils.stringToLocalDate(dobText);
                return Optional.of(new PatientDTOs.Patient(patientUuid,patientName,dob));
            }
        } catch (SQLException ex){
            System.err.println("Failed to find patient with this uuid " + uuid + " cause: " + ex.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public Optional<PatientDTOs.Patient> update(PatientDTOs.Patient updatedEntity) {
        return Optional.empty();
    }
    @Override
    public boolean delete(PatientDTOs.Patient entity) {
        return false;
    }
}
