package com.kenyajug.encounter.patient;
import com.kenyajug.encounter.core.EncounterDatabaseManager;
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
        var optionalPatient = repository.findById(patient.uuid());
        assertThat(optionalPatient).isNotEmpty();
        var actualPatient = optionalPatient.get();
        assertThat(actualPatient).isNotNull();
        assertThat(actualPatient.uuid()).isEqualTo(patient.uuid());
        assertThat(actualPatient.name()).isEqualTo(patient.name());
        assertThat(actualPatient.dob().isEqual(patient.dob())).isTrue();
    }
    @Test
    void update() {
    }
    @Test
    void delete() {
    }
}