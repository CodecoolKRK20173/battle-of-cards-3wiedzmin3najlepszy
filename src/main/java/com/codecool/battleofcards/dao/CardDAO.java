package com.codecool.battleofcards.dao;

import com.codecool.battleofcards.services.Card;

import java.sql.*;
import java.util.ArrayList;

public class CardDAO implements ICardDAO {
    private DatabaseConnector databaseConnector;

    public CardDAO(){
        this.databaseConnector = new DatabaseConnector();
        databaseConnector.connectToDatabase();
        createSampleTable();
        insertSampleToTable();
    }

    private void createSampleTable() {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            stmt = databaseConnector.c.createStatement();
            String sql = "CREATE TABLE CARDS " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " STRENGTH       INT     NOT NULL, " +
                    " RAPIDITY       INT     NOT NULL, " +
                    " MAGICPOWER     INT     NOT NULL, " +
                    " DEFENCE        INT     NOT NULL, " +
                    " INTELLIGENCE   INT     NOT NULL)";
            System.out.println("Jestem tu");
            stmt.executeUpdate(sql);

            stmt.close();
            databaseConnector.c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void insertSampleToTable() {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);

            stmt = databaseConnector.c.createStatement();
            String sql = "INSERT INTO CARDS (ID,NAME,STRENGTH,RAPIDITY,MAGICPOWER,DEFENCE,INTELLIGENCE) " +
                    "VALUES (1, 'Imie1', 10, 10, 10, 10, 10);";
            stmt.executeUpdate(sql);

            stmt.close();
            databaseConnector.c.commit();
            databaseConnector.c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Insert successfully");
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

            stmt = databaseConnector.c.createStatement();
            String sql = "UPDATE CARDS\n" +
                    "SET\n" +
                    " NAME = '"+ name + "',\n" +
                    " STRENGTH ="+ String.valueOf(strength) + "\n" +
                    " WHERE\n" +
                    " ID=" + String.valueOf(id) + ";";
            stmt.executeUpdate(sql);
            databaseConnector.c.commit();


            stmt.close();
            databaseConnector.c.commit();
            databaseConnector.c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Updated successfully");
    }

    @Override
    public void delete(int id) {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = databaseConnector.c.createStatement();
            String sql = "DELETE from CARDS where ID= "+ id + ";";
            stmt.executeUpdate(sql);

            stmt.close();
            databaseConnector.c.commit();
            databaseConnector.c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Deleted successfully");
    }

    @Override
    public ArrayList<Card> getAllCards(){
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
                int  magicPower = rs.getInt("magicpower");
                int defence = rs.getInt("defence");
                int intelligence = rs.getInt("intelligence");
                cardsList.add(new Card(name, strength, rapidity, magicPower, defence, intelligence));
            }

            rs.close();
            stmt.close();
            databaseConnector.c.close();
        } catch ( SQLException e ) {

        }
        System.out.println("Operation done successfully");

        return cardsList;
    }

    @Override
    public Card getCardById(int id){
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
            int  magicPower = rs.getInt("magicpower");
            int defence = rs.getInt("defence");
            int intelligence = rs.getInt("intelligence");

            card = new Card(name, strength, rapidity, magicPower, defence, intelligence);

            rs.close();
            stmt.close();
            databaseConnector.c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return null;
        }
        System.out.println("Operation done successfully");

        return card;
    }
}
