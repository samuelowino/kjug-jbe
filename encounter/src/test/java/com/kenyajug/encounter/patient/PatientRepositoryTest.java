package com.kenyajug.encounter.patient;
import com.kenyajug.encounter.core.EncounterDatabaseManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    @BeforeEach
    public void setUp(){
        databaseManager.dropDatabase();
    }
    @Test
    void shouldSavePatientTest() {
        var dob = LocalDate.of(1990,4,12);
        var patient = new Patient("7afadb2a-ffb9-404e-8156-041371c5e3c6","Bruce Lee", dob);
        var isSaved = repository.save(patient);
        assertThat(isSaved).isTrue();
    }
    @Test
    void deleteById() {
    }
    @Test
    void findById() {
    }
    @Test
    void update() {
    }
    @Test
    void delete() {
    }
    @AfterEach
    void tearDown() {
    }
}