package com.kenyajug.encounter.patient;
import com.kenyajug.encounter.core.EncounterDatabaseManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class PatientRepositoryTestConfig {
    // 1. SQLite Database Connection
    // 3. Repository
    @Bean
    public EncounterDatabaseManager encounterDatabaseManager(){
        return new EncounterDatabaseManager();
    }
    @Bean
    public PatientRepository patientRepository(){
        return new PatientRepository(encounterDatabaseManager());
    }
}
