package com.codecool.battleofcards.controllers;

import java.util.ArrayList;
import java.util.List;

import com.codecool.battleofcards.dao.CardDAO;
import com.codecool.battleofcards.services.*;
import com.codecool.battleofcards.views.EditorView;
import com.codecool.battleofcards.views.GameView;

public class TableController{
    private Table table;
    private GameView gameView;
    private EditorView editorView;
    private CardDAO cardDAO;


    public TableController(){
        this.gameView = new GameView();
        this.editorView = new EditorView();
        this.cardDAO = new CardDAO();
    }


    public void playGame(){   
        this.table = new Table(getPlayers(2));
        table.initializeGame();
        while(table.checkIfGameOn()){
            playRound();
        }
        gameView.printResultMessage(table.getWinner().getName() + " wins!");      
    }

    
    public void playRound(){
        Player activePlayer = table.getCurrentPlayer();
        System.out.println(activePlayer.getName() + "s" + "turn");
        gameView.showCard(activePlayer.getTopCard());
        int attr = gameView.getAttribute();
        int result = table.compareCards(attr);
        switch (result) {
            case 1:
                gameView.printResultMessage(table.getCurrentPlayer() + " wins the round");
                break;

            case 0:
                List<Player> players = table.getPlayersInRound();
                gameView.printResultMessage("WAR!!!! Players: ");
                for (Player player : players) {
                   gameView.printResultMessage(player.getName()); 
                }
                break;

            case -1:
                gameView.print("You lost! Now its " + table.getCurrentPlayer().getName() + "s turn." );
                break;
        }
    }

    public void initializeGame(){

    }


    public List<Player> getPlayers(int number){
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < number; i++) { 
            String name = gameView.getUserName();
            Player player = new Player(name); 
            players.add(player);  
        }
        players.get(0).setActivePlayer();
        return players;
    }

    public void run(){
        gameView.printMenu();
        boolean isGameOn = true;
        while(isGameOn){ 
            int choice = gameView.getNumericInput();    
            switch (choice) {
                case 1:
                    playGame(); 
                    break;
                case 2:
                    String name = editorView.getCardName();
                    List<Integer> attributesToAdd = editorView.getAttributesValues(); // zmienic zeby przyjmowalo tylko dodatnie indeksy
                    cardDAO.addNewCard(name, attributesToAdd);
                    break;

                case 3:
                    int cardId = editorView.getCardId();
                    List<Integer> attributesToEdit = editorView.getAttributesValues();
                    cardDAO.update(name, attributesToEdit);
                    break;
            
                case 4:
                    int cardIdToDelete = editorView.getCardId();
                    cardDAO.update(cardIdToDelete);
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