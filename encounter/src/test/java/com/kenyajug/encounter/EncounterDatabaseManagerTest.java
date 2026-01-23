package com.kenyajug.encounter;

import com.kenyajug.encounter.core.EncounterDatabaseManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.SQLException;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class EncounterDatabaseManagerTest {
    @Autowired //Dependency Injection
    private EncounterDatabaseManager encounterDatabaseManager;
    @Test
    public void getConnectionTest() throws SQLException {
        var connection = encounterDatabaseManager.getConnection();
        assertThat(connection).isNotNull();
        assertThat(encounterDatabaseManager.databaseName).isEqualTo("encounterdbtest");
    }
    @Test
    public void createSchemaTest(){
        var didSucceed = encounterDatabaseManager.createSchema();
        assertThat(didSucceed).isTrue();
    }
}
