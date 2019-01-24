package com.codecool.battleofcards.services;

import com.codecool.battleofcards.dao.CardDAO;

import java.util.*;

public class Table {
    public List<Player> players = new ArrayList<Player>();
    private List<Player> playersInRound = new ArrayList<Player>();
    Dealer dealer = new Dealer();
    Deck deck = new Deck();

    public Table(List<Player> players) {
        this.players = players;
        // dealer.addCards(deck.getCards());
        // dealer.dealTo(players);
        playersInRound.addAll(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

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

        if (!drawers.isEmpty()) {
            return 0;
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

    public void initializeGame() {
        dealer.addCards(deck.getCards());
        dealer.dealTo(players);
    }

    public Player getWinner() {
        Player winner = getCurrentPlayer();
        for (Player player : players) {
            if (player.getCards().size() > winner.getCards().size()) {
                winner = player;
            }
        }
        return winner;

    }
    public boolean gameDraw(){
        Player winner = getWinner();
        int i = 0;
        for (Player player : players) {
            if(player.getCards().size() == winner.getCards().size())
                i += 1;
                if(i > 1)
                    return true;
        }

        return false;
    }

    public List<Player> handleGameDraw(){
        List<Player> winners = new ArrayList<>();
        Player winner = getWinner();
        winners.add(winner);

        for (Player player : players) {
            if(player.getCards().size() == winner.getCards().size())
                if (player != winner)
                    winners.add(player);
        }

        return winners;
    }
}