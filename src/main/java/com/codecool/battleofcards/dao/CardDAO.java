package com.codecool.battleofcards.dao;

import com.codecool.battleofcards.services.Card;
import com.codecool.battleofcards.views.EditorView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CardDAO implements ICardDAO {
    private DatabaseConnector databaseConnector;
    EditorView view = new EditorView();

    public CardDAO() {
        this.databaseConnector = DatabaseConnector.getInstance();
        databaseConnector.connectToDatabase();
        createSampleTable();
        insertSampleToTable();
    }

    private void createSampleTable() {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            stmt = databaseConnector.c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS CARDS " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NAME           TEXT    NOT NULL, " +
                    " STRENGTH       INT     NOT NULL, " +
                    " RAPIDITY       INT     NOT NULL, " +
                    " MAGICPOWER     INT     NOT NULL, " +
                    " DEFENCE        INT     NOT NULL, " +
                    " INTELLIGENCE   INT     NOT NULL)";
            stmt.executeUpdate(sql);

            stmt.close();
            databaseConnector.c.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        view.println("Table created successfully");
    }

    private void insertSampleToTable() {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);

            stmt = databaseConnector.c.createStatement();
            String sql = "INSERT INTO CARDS (NAME,STRENGTH,RAPIDITY,MAGICPOWER,DEFENCE,INTELLIGENCE) " +
                    "VALUES ('SampleName', 10, 10, 10, 10, 10);";
            stmt.executeUpdate(sql);

            stmt.close();
            databaseConnector.c.commit();
            databaseConnector.c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        view.println("Insert successfully");
    }

    @Override
    public void insertNew(String name, List<Integer> stats) throws DAOException {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);

            stmt = databaseConnector.c.createStatement();
            String sql = "INSERT INTO CARDS (NAME,STRENGTH,RAPIDITY,MAGICPOWER,DEFENCE,INTELLIGENCE) " +
                    "VALUES ('" + name + "', " + stats.get(0) + ", " + stats.get(1) + ", " + stats.get(2) + ", " + stats.get(3) + ", " + stats.get(4) + ");";
            stmt.executeUpdate(sql);

            stmt.close();
            databaseConnector.c.commit();
            databaseConnector.c.close();
        } catch (SQLException e) {
            throw new DAOException("Exception occured during inserting new card to database");
        }
        view.println("Records created successfully");
    }

    @Override
    public void update(int id, List<Integer> stats) throws DAOException {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);

            stmt = databaseConnector.c.createStatement();
            String sql = "UPDATE CARDS\n" +
                    "SET\n" +
                    " STRENGTH =" + String.valueOf(stats.get(0)) + ",\n" +
                    " RAPIDITY =" + String.valueOf(stats.get(1)) + ",\n" +
                    " MAGICPOWER =" + String.valueOf(stats.get(2)) + ",\n" +
                    " DEFENCE =" + String.valueOf(stats.get(3)) + ",\n" +
                    " INTELLIGENCE =" + String.valueOf(stats.get(4)) + "\n" +
                    " WHERE\n" +
                    " ID=" + String.valueOf(id) + ";";
            stmt.executeUpdate(sql);
            databaseConnector.c.commit();


            stmt.close();
            databaseConnector.c.commit();
            databaseConnector.c.close();
        } catch (Exception e) {
            throw new DAOException("Exception occured during updating existing card in database");
        }
        view.println("Updated successfully");
    }

    @Override
    public void delete(int id) throws DAOException {
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);

            stmt = databaseConnector.c.createStatement();
            String sql = "DELETE from CARDS where ID= " + id + ";";
            stmt.executeUpdate(sql);

            stmt.close();
            databaseConnector.c.commit();
            databaseConnector.c.close();
        } catch (Exception e) {
            throw new DAOException("Exception occured during deletion existing card in database");
        }
        view.println("Deleted successfully");
    }

    @Override
    public ArrayList<Card> getAllCards() throws DAOException {
        ArrayList<Card> cardsList = new ArrayList<>();
        Statement stmt = null;

        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);

            stmt = databaseConnector.c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CARDS");

            while (rs.next()) {
                String name = rs.getString("name");
                int strength = rs.getInt("strength");
                int melee = rs.getInt("rapidity");
                int magic = rs.getInt("magicpower");
                int dexterity = rs.getInt("defence");
                int intelligence = rs.getInt("intelligence");
                int cardId = rs.getInt("id");
                cardsList.add(new Card(strength, melee, magic, dexterity, intelligence, name, cardId));
            }

            rs.close();
            stmt.close();
            databaseConnector.c.close();
        } catch (SQLException e) {
            throw new DAOException("Exception occured during geting all existing cards in database");
        }
        view.println("Operation done successfully");

        return cardsList;
    }

    @Override
    public Card getCardById(int id) throws DAOException {
        Card card;

        Statement stmt = null;
        try {
            databaseConnector.connectToDatabase();
            databaseConnector.c.setAutoCommit(false);

            stmt = databaseConnector.c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CARDS WHERE ID = " + id);

            String name = rs.getString("name");
            int strength = rs.getInt("strength");
            int melee = rs.getInt("rapidity");
            int magic = rs.getInt("magicpower");
            int dexterity = rs.getInt("defence");
            int intelligence = rs.getInt("intelligence");
            int cardId = rs.getInt("id");

            card = new Card(strength, melee, magic, dexterity, intelligence, name, cardId);

            rs.close();
            stmt.close();
            databaseConnector.c.close();
        } catch (Exception e) {
            throw new DAOException("Exception occured during geting existing card by ID");
        }
        view.println("Operation done successfully");

        return card;
    }
}
