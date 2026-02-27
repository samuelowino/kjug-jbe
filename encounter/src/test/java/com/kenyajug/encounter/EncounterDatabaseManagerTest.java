package com.kenyajug.encounter;

import com.kenyajug.encounter.core.EncounterDatabaseManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EncounterDatabaseManagerTest {
    @Autowired //Dependency Injection
    private EncounterDatabaseManager encounterDatabaseManager;
    @Test
    public void getConnectionTest() {
        var connection = encounterDatabaseManager.getConnection();
        assertThat(connection).isNotNull();
        assertThat(encounterDatabaseManager.databaseName).isEqualTo("encounterdbtest");
    }
    @Order(1)
    @Test
    public void createSchemaTest(){
        var didSucceed = encounterDatabaseManager.createSchema();
        assertThat(didSucceed).isTrue();
    }
    @Test
    @Order(2)
    @Tag("createSchemaTest")
    public void shouldDropDatabaseTest(){
        var didDelete = encounterDatabaseManager.dropDatabase();
        assertThat(didDelete).isTrue();
    }
    @BeforeAll
    public static void deleteDatabaseFile() throws IOException {
        Files.deleteIfExists(Path.of("encounterdbtest"));
    }
}
