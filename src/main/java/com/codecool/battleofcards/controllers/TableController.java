package com.codecool.battleofcards.controllers;

import com.codecool.battleofcards.dao.CardDAO;
import com.codecool.battleofcards.services.*;
import com.codecool.battleofcards.views.EditorView;
import com.codecool.battleofcards.views.GameView;

public class TableController{
    private Table table;
    private GameView gameView;
    private EditorView editorView;
    private CardDAO cardDAO;

    public void playGame(){

    }

    public void playRound(){
        table.checkIfGameOn();


    }

    public void initializeGame(){
        table = new Table();
        gameView = new GameView();
    }
}