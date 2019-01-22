package com.codecool.battleofcards.dao;

import com.codecool.battleofcards.services.Card;
import java.util.ArrayList;


public interface ICardDAO {

    void createSampleTable();
    void insertNew(String name, int strength, int rapidity, int magicPower, int defence, int intelligence);
    void update(int id, String name, int strength, int rapidity, int magicPower, int defence, int intelligence);
    void delete(int id);
    ArrayList<Card> getAllCards();
    Card getCardById(int id);
}
