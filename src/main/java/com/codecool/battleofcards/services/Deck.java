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

        // cards.add(new Card(10, 10, 2, 4, 5, "Rowan", 1));
        // cards.add(new Card(3, 4, 6, 8, 3, "Uhaaaa", 2));
        // cards.add(new Card(5, 5, 2, 4, 5, "Aria", 3));
        // cards.add(new Card(1, 8, 2, 4, 5, "Mary", 4));
        // cards.add(new Card(10, 10, 2, 4, 5, "Hacker", 5));
        // cards.add(new Card(10, 10, 2, 4, 5, "Iamtheone", 6));
        // cards.add(new Card(10, 10, 2, 4, 5, "Rowan", 7));
        // cards.add(new Card(3, 4, 6, 8, 3, "Uhaaaa", 8));
        // cards.add(new Card(5, 5, 2, 4, 5, "Aria", 9));
        // cards.add(new Card(1, 8, 2, 4, 5, "Mary", 10));
        // cards.add(new Card(10, 10, 2, 4, 5, "Hacker", 11));
        // cards.add(new Card(10, 10, 2, 4, 5, "Iamtheone", 12));
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