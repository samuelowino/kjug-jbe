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
        var isSaved = repository.save(patient);
        assertThat(isSaved).isTrue();
    }
    @Test
    void shouldDeleteByIdTest() {
        var isSaved = repository.save(patient);
        assertThat(isSaved).isTrue();
        var isDeleted = repository.deleteById(patient.uuid());
        assertThat(isDeleted).isTrue();
    }
    @Test
    void shouldFindPatientByUuidTest() {
        var isSaved = repository.save(patient);
        assertThat(isSaved).isTrue();
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
        var isSaved = repository.save(patient);
        assertThat(isSaved).isTrue();
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
        assertThat(updateResult instanceof Result.Success<Boolean>).isTrue();
        var isUpdated = ((Result.Success<Boolean>) updateResult).value();
        assertThat(isUpdated).isTrue();
    }
    @Test
    void delete() {
    }
}