package com.kenyajug.encounter.core;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;

// 1. Open a database connection to sqlite
// 2. Initialize our database
//      - Creating the database
//      - Create database tables
//      - Delete/Drop database tables or entire database (Testing)
@Component
public class EncounterDatabaseManager {
    @Value("${encounter.database}")
    public String databaseName;
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + databaseName);
        } catch (SQLException ex){
            throw new RuntimeException("Failed to open con " + ex.getMessage());
        }
    }
    public boolean createSchema() {
        if(!(getConnection() instanceof Connection connection)) {
            return false;
        }
        try {
            var createTablesSQL = """
                CREATE TABLE IF NOT EXISTS Patient(
                uuid TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                dob TEXT NOT NULL
                );
         
                """;
            var statement = connection.createStatement();
            statement.execute(createTablesSQL);
            var warning = statement.getWarnings();
            if (warning != null && !warning.getMessage().isBlank())
                System.out.println("Error " + warning.getMessage());
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean dropDatabase(){
        try{
            Files.delete(Path.of(databaseName));
            var exists = Files.exists(Path.of(databaseName));
            if (!exists)
                return true;
        } catch (IOException e) {
            System.err.println("Failed to delete file " + databaseName + " cause " + e.getMessage());
        }
        return false;
    }
}






