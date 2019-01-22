package com.codecool.battleofcards.services;

import com.codecool.battleofcards.dao.CardDAO;

import java.util.*;

public class Table{
    private List<Player> players = new ArrayList<Player>();
    Dealer dealer = new Dealer();
    Deck deck = new Deck();

/*    public Table() {
        dealer = new Dealer();
        deck = new Deck();
    }*/

    public int compareCards(int atrIndex) {
        return 0;
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