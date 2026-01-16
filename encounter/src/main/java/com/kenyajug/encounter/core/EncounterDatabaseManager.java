package com.kenyajug.encounter.core;
import org.springframework.beans.factory.annotation.Value;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// 1. Open a database connection to sqlite
// 2. Initialize our database
//      - Creating the database
//      - Create database tables
//      - Delete/Drop database tables or entire
public class EncounterDatabaseManager {
    @Value("encounter.database")
    public String databaseName;
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + databaseName);
    }
}
