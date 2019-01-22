package com.codecool.battleofcards.dao;

import com.codecool.battleofcards.services.Card;

import java.sql.*;
import java.util.ArrayList;

public class CardDAO implements ICardDAO {
    private DatabaseConnector databaseConnector;

    CardDAO(){
        this.databaseConnector = new DatabaseConnector();
        databaseConnector.connectToDatabase();
        createSampleTable();
    }

    @Override
    public void createSampleTable() {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();

            stmt = databaseConnector.c.createStatement();
            String sql = "CREATE TABLE CARDS " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " STRENGTH       INT     NOT NULL, " +
                    " RAPIDITY       INT     NOT NULL, " +
                    " MAGIC POWER    INT     NOT NULL, " +
                    " DEFENCE        INT     NOT NULL, " +
                    " INTELLIGENCE    INT     NOT NULL";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO CARDS (ID,NAME,STRENGTH,RAPIDITY,MAGIC POWER,DEFENCE,INTELLIGENCE) " +
                    "VALUES (1, 'Imie1', 10, 10, 10, 10, 10);";
            stmt.executeUpdate(sql);

            stmt.close();
            databaseConnector.c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    @Override
    public void insertNew(String name, int strength, int rapidity, int magicPower, int defence, int intelligence) {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = databaseConnector.c.createStatement();
            String sql = "INSERT INTO CARDS (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (" + name + ", " + strength + ", " + rapidity + ", " + magicPower + ", " + defence + ", " + intelligence + ");";
            stmt.executeUpdate(sql);

            stmt.close();
            databaseConnector.c.commit();
            databaseConnector.c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    @Override
    public void update(int id, String name, int strength, int rapidity, int magicPower, int defence, int intelligence) {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = databaseConnector.c.createStatement();
            String sql = "UPDATE CARDS SET name = " + name + " , "
                    + "strength = " + strength + " , "
                    + "rapidity = " + rapidity + " , "
                    + "magic power = " + magicPower + " , "
                    + "defence = " + defence + " , "
                    + "intelligence = " + intelligence + " , "
                    + "WHERE id = " + id;
            stmt.executeUpdate(sql);

            stmt.close();
            databaseConnector.c.commit();
            databaseConnector.c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    @Override
    public void delete(int id) {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = databaseConnector.c.createStatement();
            String sql = "DELETE from CARDS where ID= "+ 2 + ";";
            stmt.executeUpdate(sql);

            stmt.close();
            databaseConnector.c.commit();
            databaseConnector.c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    @Override
    public ArrayList<Card> getAllCards() {
        ArrayList<Card> cardsList = new ArrayList<>();

        Statement stmt = null;
        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = databaseConnector.c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CARDS");

            while ( rs.next() ) {
                String name = rs.getString("name");
                int  strength = rs.getInt("strength");
                int rapidity  = rs.getInt("rapidity");
                int  magicPower = rs.getInt("magic power");
                int defence = rs.getInt("defence");
                int intelligence = rs.getInt("inteligence");
                cardsList.add(new Card(name, strength, rapidity, magicPower, defence, intelligence));
            }

            rs.close();
            stmt.close();
            databaseConnector.c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return cardsList;
    }

    @Override
    public Card getCardById(int id) {
        Card card;

        Statement stmt = null;
        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = databaseConnector.c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CARDS WHERE ID = " + id);

            String name = rs.getString("name");
            int  strength = rs.getInt("strength");
            int rapidity  = rs.getInt("rapidity");
            int  magicPower = rs.getInt("magic power");
            int defence = rs.getInt("defence");
            int intelligence = rs.getInt("inteligence");

            card = new Card(name, strength, rapidity, magicPower, defence, intelligence);

            rs.close();
            stmt.close();
            databaseConnector.c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return card;
    }
}
