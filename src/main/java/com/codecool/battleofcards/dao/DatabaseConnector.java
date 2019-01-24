package com.codecool.battleofcards.dao;

import java.sql.Connection;
import java.sql.DriverManager;

class DatabaseConnector {
    private static DatabaseConnector single_instance = null;
    Connection c;

    private DatabaseConnector(){

    }

    static DatabaseConnector getInstance() {
        if (single_instance == null)
            single_instance = new DatabaseConnector();

        return single_instance;
    }

    void connectToDatabase() {
        c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
