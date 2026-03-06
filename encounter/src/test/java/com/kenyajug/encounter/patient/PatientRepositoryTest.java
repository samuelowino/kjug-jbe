package com.kenyajug.encounter.patient;
import com.kenyajug.encounter.core.EncounterDatabaseManager;
import com.kenyajug.encounter.core.Result;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static com.kenyajug.encounter.patient.PatientDTOs.Patient;
@SpringBootTest(classes = PatientRepositoryTestConfig.class)
class PatientRepositoryTest {
    @Autowired
    private EncounterDatabaseManager databaseManager;
    @Autowired
    private PatientRepository repository;
    private Patient patient;
    @BeforeEach
    public void setUp(){
        databaseManager.createSchema();
        var dob = LocalDate.of(1990,4,12);
        patient = new Patient("7afadb2a-ffb9-404e-8156-041371c5e3c6","Bruce Lee", dob);
    }
    @AfterEach
    void tearDown() {
        databaseManager.dropDatabase();
    }
    @Test
    @RepeatedTest(3)
    void shouldSavePatientTest() {
        var result = repository.save(patient);
        assertThat(result).isInstanceOf(Result.Success.class);
    }
    @Test
    void shouldDeleteByIdTest() {
        var saveResult = repository.save(patient);
        assertThat(saveResult).isInstanceOf(Result.Success.class);
        var result = repository.deleteById(patient.uuid());
        assertThat(result).isInstanceOf(Result.Success.class);
    }
    @Test
    void shouldFindPatientByUuidTest() {
        var saveResult = repository.save(patient);
        assertThat(saveResult).isInstanceOf(Result.Success.class);
        Result<Patient> result = repository.findById(patient.uuid());
        assertThat(result instanceof Result.Success<Patient>).isTrue();
        var actualPatient = ((Result.Success<Patient>) result).value();
        assertThat(actualPatient).isNotNull();
        assertThat(actualPatient.uuid()).isEqualTo(patient.uuid());
        assertThat(actualPatient.name()).isEqualTo(patient.name());
        assertThat(actualPatient.dob().isEqual(patient.dob())).isTrue();
    }
    @Test
    void shouldUpdatePatientTest() {
        var saveResult = repository.save(patient);
        assertThat(saveResult).isInstanceOf(Result.Success.class);
        var result = repository.findById(patient.uuid());
        assertThat(result instanceof Result.Success<Patient>).isTrue();
        var actualPatient = ((Result.Success<Patient>) result).value();
        assertThat(actualPatient.uuid()).isEqualTo(patient.uuid());
        assertThat(actualPatient.name()).isEqualTo(patient.name());
        assertThat(actualPatient.dob().isEqual(patient.dob()));
        var newDob = LocalDate.of(2004,8,17);
        var newPatientName = "New Patient Name";
        var expectedUpdatedPatient = new Patient(patient.uuid(),newPatientName,newDob);
        var updateResult = repository.update(expectedUpdatedPatient);
        IO.println(updateResult);
        assertThat(updateResult).isInstanceOf(Result.Success.class);
    }
    @Test
    void shouldDeleteTest() {
        var saveResult = repository.save(patient);
        assertThat(saveResult).isInstanceOf(Result.Success.class);
        var result = repository.delete(patient);
        assertThat(result).isInstanceOf(Result.Success.class);
    }
}