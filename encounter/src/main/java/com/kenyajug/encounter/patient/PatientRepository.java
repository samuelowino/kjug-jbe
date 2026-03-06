package com.kenyajug.encounter.patient;
import com.kenyajug.encounter.core.DateTimeUtils;
import com.kenyajug.encounter.core.EncounterDatabaseManager;
import com.kenyajug.encounter.core.EncounterRepository;
import com.kenyajug.encounter.core.Result;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Repository
public class PatientRepository implements EncounterRepository<PatientDTOs.Patient,String> {
    private final EncounterDatabaseManager databaseManager;
    public PatientRepository(EncounterDatabaseManager encounterDatabaseManager){
        databaseManager = encounterDatabaseManager;
    }
    @Override
    public Result<Void> save(PatientDTOs.Patient entity) {
        if(!(databaseManager.getConnection() instanceof Connection connection))
            return Result.failure("Failed to open database connection");
        try(connection){
            var savePatientQuery = """
                INSERT INTO Patient VALUES(?,?,?);
                """;
            var patientDob = DateTimeUtils.localDateToString(entity.dob());
            var statement = connection.prepareStatement(savePatientQuery);
            statement.setString(1,entity.uuid());
            statement.setString(2,entity.name());
            statement.setString(3, patientDob);
            if(statement.executeUpdate() == 1){
                return Result.success(null);
            } else return Result.failure("Failed to create new patient, unknown error occurred.");
        } catch (SQLException ex){
            return Result.failure("Failed to create new patient: " + ex.getLocalizedMessage());
        }
    }
    @Override
    public Result<Void> deleteById(String uuid) {
        var connection = Result.of(() -> databaseManager.getConnection()).orElseThrow();
        try(connection){
            var sql = """
                DELETE FROM Patient
                WHERE uuid = ?;
                """;
            Result<PreparedStatement> statementResult = getPreparedStatementResult(connection, sql);
            return switch (statementResult){
                case Result.Failure<PreparedStatement> failure -> Result.failure(failure.error());
                case Result.Success<PreparedStatement> (PreparedStatement statement) -> {
                    try(statement){
                        statement.setString(1,uuid);
                        if (statement.executeUpdate() == 1) {
                            yield Result.success(null);
                        } else yield Result.failure("Patient with this id does not exist: " + uuid);
                    } catch (SQLException ex){
                        yield Result.failure("Failed to delete patient by uuid " + uuid + " cause " + ex.getLocalizedMessage());
                    }
                }
            };
        } catch (SQLException ex){
            return Result.failure("Failed to delete patient, error occurred: " + ex.getLocalizedMessage());
        }
    }
    @Override
    public Result<PatientDTOs.Patient> findById(String uuid) {
        var connection = Result.of(() -> databaseManager.getConnection()).orElseThrow();
        try (connection){
            var sql = """
                SELECT * FROM Patient
                WHERE uuid = ?;
                """;
            Result<PreparedStatement> statementResult = getPreparedStatementResult(connection, sql);
            if ((statementResult instanceof Result.Failure<PreparedStatement> (Exception error)))
                return Result.failure(error); //guard

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
    public Result<Void> update(PatientDTOs.Patient updatedEntity) {
        return switch (findById(updatedEntity.uuid())) {
            case Result.Failure<PatientDTOs.Patient> failure -> Result.failure("Failed to update patient, patient with this id does not exist: " + failure.error().getLocalizedMessage());
            case Result.Success<PatientDTOs.Patient> _ -> {
                if(!(databaseManager.getConnection() instanceof Connection connection)) {
                    yield Result.failure("Failed to connect to db");
                }
                try (connection) {
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
                        yield Result.success(null);
                    }
                } catch (SQLException ex){
                    yield Result.failure(ex.getLocalizedMessage());
                }
            }
        };

    }
    @Override
    public Result<Void> delete(PatientDTOs.Patient entity) {
        return deleteById(entity.uuid());
    }
    private static Result<PreparedStatement> getPreparedStatementResult(Connection resultConnect, String sql) {
        return Result.of(() -> resultConnect.prepareStatement(sql));
    }
}
