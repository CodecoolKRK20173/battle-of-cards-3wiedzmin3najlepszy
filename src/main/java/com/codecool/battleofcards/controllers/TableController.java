package com.codecool.battleofcards.controllers;

import java.util.ArrayList;
import java.util.List;

import com.codecool.battleofcards.dao.CardDAO;
import com.codecool.battleofcards.services.*;
import com.codecool.battleofcards.views.EditorView;
import com.codecool.battleofcards.views.GameView;

public class TableController {
    private Table table;
    private GameView gameView;
    private EditorView editorView;
    private CardDAO cardDAO;

    public TableController() {
        this.gameView = new GameView();
        this.editorView = new EditorView();
        // this.cardDAO = new CardDAO();
    }

    public void playGame() {
        int number = gameView.getNumberOfPlayers();
        this.table = new Table(getPlayers(number));
        List<Player> winners = new ArrayList<>();
        table.initializeGame();
        gameView.clearScreen();
        while (table.checkIfGameOn()) {
            for (Player player : table.getPlayers()) {
                System.out.println(player.getName() + "\'s  remaining cards: " + player.getCards().size());
            }
            playRound();
        }

        if (!table.gameDraw()) {
            gameView.printWinningMessage(table.getWinner().getName(), table.getPlayers());
        }
        else{
            winners = table.handleGameDraw();
            String sWinners = "";
            for (int i = 0; i < winners.size(); i++){
                Player winner = winners.get(i);
                sWinners += winner.getName();
                if (i != winners.size() - 1)
                    sWinners += " and ";

            }
            gameView.printWinningMessage(sWinners, table.getPlayers());
        }

    }

    public void playRound() {
        Player activePlayer = table.getCurrentPlayer();
        gameView.showCard(activePlayer.getTopCard(), activePlayer.getName());
        int attr = gameView.getAttribute();
        int result = table.compareCards(attr);
        switch (result) {
            case 1:
                gameView.printResultMessage(table.getCurrentPlayer().getName() + " wins the round!");
                break;

            case 0:
                List<Player> playersInWar = table.getPlayersInRound();
                gameView.println("WAR!!!! Players participating: ");
                for (Player player : playersInWar) {
                    gameView.println(player.getName());
                }
                break;

            case -1:
                gameView.println("You lost! Now its " + table.getCurrentPlayer().getName() + "\'s turn.");
                break;
        }
    }


    public List<Player> getPlayers(int number) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String name = gameView.getUserName();
            Player player = new Player(name);
            players.add(player);
        }
        players.get(0).setActivePlayer(true);
        return players;
    }


    public void run() {
        gameView.clearScreen();
        boolean isGameOn = true;
        while (isGameOn) {
            gameView.printMenu();
            int choice = gameView.getNumericInput();
            switch (choice) {
                case 1:
                    playGame();
                    break;
                case 2:
                    String name = editorView.getCardName();
                    List<Integer> attributesToAdd = editorView.getAttributesValues(); // zmienic zeby przyjmowalo tylko
                    // dodatnie indeksy
                    // cardDAO.addNewCard(name, attributesToAdd);
                    break;

                case 3:
                    int cardId = editorView.getCardId();
                    List<Integer> attributesToEdit = editorView.getAttributesValues();
                    // cardDAO.update(name, attributesToEdit);
                    break;

                case 4:
                    int cardIdToDelete = editorView.getCardId();
                    // cardDAO.update(cardIdToDelete);
                    break;

                case 5:
                    isGameOn = false;
                    break;

                default:
                    System.out.println("Wrong choice!");
            }
        }
    }
}