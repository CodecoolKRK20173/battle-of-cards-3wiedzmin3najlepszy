package com.codecool.battleofcards.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dealer {
    ArrayList<Card> cardsToSend = new ArrayList<>();

    public void dealTo(Player player) {
        player.addCards(cardsToSend);
        cardsToSend.clear();
    }

    public void addCards(ArrayList<Card> cards) {
        cardsToSend.addAll(cards);
    }

    public void addCard(Card card) {
        cardsToSend.add(card);
    }

    public void dealTo(List<Player> players) {
        Iterator<Card> iter = cardsToSend.iterator();
        while (!cardsToSend.isEmpty()) {
            for (Player player : players) {
                Card card = iter.next();
                player.addCards(card);
                iter.remove();
                if (cardsToSend.isEmpty()) {
                    break;
                }
            }
        }
        cardsToSend.clear();
    }

}