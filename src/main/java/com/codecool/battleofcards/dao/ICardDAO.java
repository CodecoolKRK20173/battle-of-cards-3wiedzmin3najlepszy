package com.codecool.battleofcards.dao;

import com.codecool.battleofcards.services.Card;

import java.util.ArrayList;


public interface ICardDAO {

    void insertNew(String name, int strength, int rapidity, int magicPower, int defence, int intelligence) throws DAOException;

    void update(int id, String name, int strength, int rapidity, int magicPower, int defence, int intelligence) throws DAOException;

    void delete(int id) throws DAOException;

    ArrayList<Card> getAllCards() throws DAOException;

    Card getCardById(int id) throws DAOException;
}
