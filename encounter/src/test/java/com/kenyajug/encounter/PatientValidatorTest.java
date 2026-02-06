package com.kenyajug.encounter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static com.kenyajug.encounter.patient.PatientDTOs.Patient;
public class PatientValidatorTest {
    @Test
    @DisplayName("Check if patient name is blank, otherwise throw")
    public void throws_ifPatientIsInvalidTest(){
        var uuid = "d4d5e81f-c932-4516-81ce-80f2da66ee7f";
        var invalidUuid = "";
        var invalidName = "";
        var validName = "Bruce Wayne";
        var invalidDob = (LocalDate) null;
        var validDob = LocalDate.of(1994,5,11);

        assertThatThrownBy(() -> new Patient(uuid,invalidName,invalidDob))
                .as("Name cannot be empty")
                .isInstanceOf(AssertionError.class);

        assertThatThrownBy(() -> new Patient(uuid,validName,invalidDob))
                .as("Dob cannot be null")
                .isInstanceOf(AssertionError.class);

        assertThatThrownBy(() -> new Patient(invalidUuid,validName,validDob))
                .as("Dob cannot be null")
                .isInstanceOf(AssertionError.class);

    }
}
