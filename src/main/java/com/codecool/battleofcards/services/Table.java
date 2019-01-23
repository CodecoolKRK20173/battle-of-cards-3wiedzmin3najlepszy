package com.codecool.battleofcards.services;

import com.codecool.battleofcards.dao.CardDAO;

import java.util.*;

import static com.codecool.battleofcards.services.AttributeType.*;

public class Table{
    private ArrayList<Player> players = new ArrayList<Player>();
    Dealer dealer = new Dealer();
    Deck deck = new Deck();

    public Table(ArrayList<Player> players) {
        this.players = players;
        dealer.addCards(deck.getCards());
        dealer.dealTo(players);
    }

    public int compareCards(int atrIndex, Card cardOne, Card cardTwo) {
        AttributeType val = AttributeType.values()[atrIndex - 1];
        CardComparator cardComparator = new CardComparator(val);
        return cardComparator.compare(cardOne, cardTwo);

    }

    public Player getCurrentPlayer(){
        for (Player player : players){
            if (player.getActivePlayer())
                return player;
        }
        return null;
    }

    public boolean checkIfGameOn(){
        for (Player player : players) {
            if (player.getCards().size() == 0)
                return false;
        }
        return true;
    }

}