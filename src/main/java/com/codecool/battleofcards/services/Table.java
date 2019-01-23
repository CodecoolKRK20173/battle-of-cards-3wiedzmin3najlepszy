package com.codecool.battleofcards.services;

import com.codecool.battleofcards.dao.CardDAO;

import java.util.*;

public class Table {
    private List<Player> players = new ArrayList<Player>();
    private List<Player> playersInRound = new ArrayList<Player>();
    Dealer dealer = new Dealer();
    Deck deck = new Deck();

    /*
     * public Table() { dealer = new Dealer(); deck = new Deck(); }
     */

    public int compareCards(int atrIndex) {
        AttributeType attrType = AttributeType.values()[atrIndex - 1];
        Comparator comparator = new CardComparator(attrType);

        int result = 0;
        Set<Player> drawers = new HashSet<>();
        Player currentPlayer = getCurrentPlayer();
        Player winner = getCurrentPlayer();
        for (Player player : playersInRound) {
            if (player != winner) {
                result = comparator.compare(winner.getTopCard(), player.getTopCard());
                if (result == 0) {
                    drawers.add(winner);
                    drawers.add(player);
                } else if (result == -1) {
                    winner = player;
                    drawers.clear();
                }
            }
        }

        if (currentPlayer != winner) {
            currentPlayer.setActivePlayer(false);
            winner.setActivePlayer(true);
        }

        // delete cards and add to dealer
        for (Player player : playersInRound) {
            dealer.addCard(player.getTopCard());
            player.deleteTopCard();
        }

        if (drawers.isEmpty()) {
            playersInRound = players;
            dealer.dealTo(winner);
        } else {
            playersInRound.clear();
            playersInRound.addAll(drawers);
        }

        return result;
    }

    public List<Player> getPlayersInRound() {
        return playersInRound;
    }

    public Player getCurrentPlayer() {
        for (Player player : players) {
            if (player.getActivePlayer())
                return player;
        }
        return null;
    }

    public boolean checkIfGameOn() {
        for (Player player : players) {
            if (player.getCards().size() == 0)
                return false;
        }
        return true;
    }

}