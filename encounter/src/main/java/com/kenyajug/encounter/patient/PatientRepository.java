package com.kenyajug.encounter.patient;
import com.kenyajug.encounter.core.DateTimeUtils;
import com.kenyajug.encounter.core.EncounterDatabaseManager;
import com.kenyajug.encounter.core.EncounterRepository;
import com.kenyajug.encounter.core.Result;
import static com.kenyajug.encounter.core.Result.failure;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        var resultConnect = Result.of(() -> databaseManager.getConnection()).orElseThrow();
        var sql = """
                DELETE FROM Patient
                WHERE uuid = ?;
                """;
        Result<PreparedStatement> statementResult = getPreparedStatementResult(resultConnect, sql);
        return switch (statementResult){
           case Result.Failure<PreparedStatement> failure -> {
               System.out.println(failure.error().getLocalizedMessage());
               yield false;
           }
           case Result.Success<PreparedStatement> success -> {
               try(PreparedStatement statement = success.value()){
                   statement.setString(1,uuid);
                   yield statement.executeUpdate() == 1;
               } catch (SQLException ex){
                   System.err.println("Failed to delete patient by uuid " + uuid + " cause " + ex.getLocalizedMessage());
                   yield false;
               }
           }
       };
    }
    @Override
    public Result<PatientDTOs.Patient> findById(String uuid) {
        var resultConnect = Result.of(() -> databaseManager.getConnection()).orElseThrow();
        try {
            var sql = """
                SELECT * FROM Patient
                WHERE uuid = ?;
                """;
            Result<PreparedStatement> statementResult = getPreparedStatementResult(resultConnect, sql);
            if ((statementResult instanceof Result.Failure<PreparedStatement> (Exception error)))
                return Result.failure(error);

            Result.of(() -> resultConnect.prepareStatement(sql));
            var statement = statementResult.orElseThrow();
            statement.setString(1,uuid);
            var resultSet = statement.executeQuery();
            if (resultSet.next()){
                var patientUuid = resultSet.getString(1);
                var patientName = resultSet.getString(2);
                var dobText = resultSet.getString(3);
                var dob = DateTimeUtils.stringToLocalDate(dobText);
                return Result.success(new PatientDTOs.Patient(patientUuid,patientName,dob));
            } else return Result.failure("Failed to find patient with this uuid " + uuid + "| empty result set");
        } catch (SQLException ex){
            System.err.println("Failed to find patient with this uuid " + uuid + " cause: " + ex.getMessage());
            return Result.failure("Failed to find patient with this uuid " + uuid + " cause: " + ex.getMessage());
        }
    }

    @Override
    public Result<Boolean> update(PatientDTOs.Patient updatedEntity) {
        return switch (findById(updatedEntity.uuid())) {
            case Result.Failure<PatientDTOs.Patient> failure -> Result.failure(failure.error().getLocalizedMessage());
            case Result.Success<PatientDTOs.Patient> success -> {
                if(!(databaseManager.getConnection() instanceof Connection connection)) {
                    yield Result.failure("Failed to connect to db");
                }
                try {
                    var sql = """
                UPDATE Patient
                SET
                name = ?,
                dob = ?
                WHERE uuid = ?;
                """;
                    var statement = connection.prepareStatement(sql);
                    var dob = DateTimeUtils.localDateToString(updatedEntity.dob());
                    statement.setString(1,updatedEntity.name());
                    statement.setString(2,dob);
                    statement.setString(3, updatedEntity.uuid());
                    var modifiedRows = statement.executeUpdate();
                    if (modifiedRows == 0) {
                        yield Result.failure("Failed to update patient: rows updated " + modifiedRows);
                    }  else {
                        yield Result.success(true);
                    }
                } catch (SQLException ex){
                    yield Result.failure(ex.getLocalizedMessage());
                }
            }
        };

    }
    @Override
    public boolean delete(PatientDTOs.Patient entity) {
        return false;
    }
    private static Result<PreparedStatement> getPreparedStatementResult(Connection resultConnect, String sql) {
        return Result.of(() -> resultConnect.prepareStatement(sql));
    }
}
