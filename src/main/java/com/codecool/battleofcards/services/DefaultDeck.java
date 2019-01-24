package com.codecool.battleofcards.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultDeck {
    List<String> names = new ArrayList<>();
    List<List<Integer>> values = new ArrayList<>();

    public DefaultDeck(){
        fillNames();
        fillValues();
    }

    public void populateDatabase(DAOInputService daoInputService){
         for (int i = 0; i < names.size(); i++){
             daoInputService.addNewCard(names.get(i),values.get(i));
         }

    }

    public void fillNames() {
        names.addAll(Arrays.asList("Leszy", "Poludnica", "Utopiec", "Bies", "Wilkolak", "Wampir", "Imlerith",
                "Nekker","Ghoul","Baba Wodna", "Emhyr var Emreis", "Kuroliszek"));

    }

    public void fillValues(){
        // STR, MELEE, MAGIC, DEX, INT
        values.addAll(Arrays.asList(new ArrayList<>(Arrays.asList(60, 10, 50, 25, 40)), //Leszy
                new ArrayList<>(Arrays.asList(50, 40, 35, 50, 15)), // Poludnica
                new ArrayList<>(Arrays.asList(20, 20, 0, 35, 5)), // Utopiec
                new ArrayList<>(Arrays.asList(70, 50, 0, 15, 10)), // Bies
                new ArrayList<>(Arrays.asList(25, 25, 10, 50, 30)), // Wilkolak
                new ArrayList<>(Arrays.asList(35, 30, 60, 40, 80)), // Wampir
                new ArrayList<>(Arrays.asList(15, 60, 50, 25, 70)), // Imlerith
                new ArrayList<>(Arrays.asList(5, 10, 5, 20, 5)), // Nekker
                new ArrayList<>(Arrays.asList(20, 15, 5, 30, 10)), // Ghoul
                new ArrayList<>(Arrays.asList(30, 15, 50, 35, 15)), // Baba Wodna
                new ArrayList<>(Arrays.asList(15, 30, 0, 50, 100)), // Emhyr
                new ArrayList<>(Arrays.asList(50, 35, 40, 30, 30)) // Kuroliszke
                ));

    }



}
