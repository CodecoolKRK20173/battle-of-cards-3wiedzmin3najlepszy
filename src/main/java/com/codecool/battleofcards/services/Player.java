package com.codecool.battleofcards.services;

import java.util.ArrayList;

public class Player{
    String name;
    boolean activePlayer;
    ArrayList<Card> cards;


    public String getName(){
        return name;
    }

    public boolean getActivePlayer(){
        return activePlayer;
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public Card getTopCard(){
        return cards.get(0);
    }

    public void deleteTopCard(){
        cards.remove(0);
    }

    public void addCards(ArrayList<Card> cardsToAdd){
        cards.addAll(cardsToAdd);
    }

    public void addCards(Card card){
        cards.add(card);
    }

    public void setActivePlayer(){
        activePlayer = true;
    }


}