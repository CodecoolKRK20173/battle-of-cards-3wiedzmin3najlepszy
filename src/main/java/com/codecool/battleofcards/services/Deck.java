package com.codecool.battleofcards.services;

import com.codecool.battleofcards.dao.CardDAO;
import com.codecool.battleofcards.dao.DAOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        CardDAO cardDao = new CardDAO();
        try {
            cards = cardDao.getAllCards();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCards(ArrayList<Card> cardsToAdd) {
        cards.addAll(cardsToAdd);
    }

}