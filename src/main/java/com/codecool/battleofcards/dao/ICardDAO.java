package com.codecool.battleofcards.dao;

import com.codecool.battleofcards.services.Card;

import java.util.ArrayList;
import java.util.List;


public interface ICardDAO {

    void insertNew(String name, List<Integer> stats) throws DAOException;

    void update(int id, String name, List<Integer> stats) throws DAOException;

    void delete(int id) throws DAOException;

    ArrayList<Card> getAllCards() throws DAOException;

    Card getCardById(int id) throws DAOException;
}
