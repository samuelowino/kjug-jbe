package com.kenyajug.encounter.patient;
import java.time.LocalDate;
public sealed interface PatientDTOs {
    record Patient(
            String uuid,
            String name,
            LocalDate dob) implements PatientDTOs {
        //Cannot be changed using reflection
        public Patient {
            if (name.isBlank()) throw new AssertionError("Name cannot be empty");
            if (dob == null) throw new AssertionError("Dob cannot be null");
            if (uuid.isBlank()) throw new AssertionError("Id cannot be empty");
        }
    }
}
