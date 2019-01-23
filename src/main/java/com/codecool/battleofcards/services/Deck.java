package com.codecool.battleofcards.services;

import com.codecool.battleofcards.dao.CardDAO;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    ArrayList<Card> cards;


    public Deck(){
        CardDAO cardDao = CardDAO.getInstance();
        cards = cardDao.getAllCards();
        shuffle();
    }


    private void shuffle(){
        Collections.shuffle(cards);
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public void addCards(ArrayList<Card> cardsToAdd){
        cards.addAll(cardsToAdd);
    }
    



}