package com.kenyajug.encounter.patient;
import com.kenyajug.encounter.core.DateTimeUtils;
import com.kenyajug.encounter.core.EncounterDatabaseManager;
import com.kenyajug.encounter.core.EncounterRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
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
            statement.execute();
            var warning = statement.getWarnings();
            if (warning != null && !warning.getMessage().isBlank()){
                return false;
            }
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteById(String s) {
        return false;
    }
    @Override
    public Optional<PatientDTOs.Patient> findById(String s) {
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
