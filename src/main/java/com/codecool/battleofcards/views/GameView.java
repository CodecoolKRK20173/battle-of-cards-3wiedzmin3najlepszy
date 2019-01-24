package com.codecool.battleofcards.views;

import java.util.Scanner;
import java.util.List;

import com.codecool.battleofcards.services.Card;
import com.codecool.battleofcards.services.Player;
import java.lang.StringBuilder;
import java.util.ArrayList;

public class GameView extends View {

    public String getUserName() {
        print("Enter user Name: ");
        String userName = scanner.nextLine();
        return userName;
    }

    public void showCard(Card card, String name) {
        println(name + "\'s" + " turn\n");
        println("Your card: " + card.getName());
        println("Statistics: \n\n" + "(1) Strength: " + card.getStrength() + "\n" + "(2) Melee: " + card.getMelee() + "\n"
                + "(3) Magic: " + card.getMagic() + "\n" + "(4) Dexterity: " + card.getDexterity() + "\n" + "(5) Intelligence: "
                + card.getIntelligence() + "\n");
    }

    public void showPlayersTopCards(List<Player> players){
        int numberOfRows = 7;
        final Object[][] table = new Object[numberOfRows][];
        List<String> names = new ArrayList<>();
        List<String> cardNames = new ArrayList<>();
        List<String> strengths = new ArrayList<>();
        List<String> melees = new ArrayList<>();
        List<String> magics = new ArrayList<>();
        List<String> dexterities = new ArrayList<>();
        List<String> intelligences = new ArrayList<>();



        // StringBuilder names = new StringBuilder();
        // StringBuilder cardNames = new StringBuilder();
        // StringBuilder strengths = new StringBuilder();
        // StringBuilder melees = new StringBuilder();
        // StringBuilder magics = new StringBuilder();
        // StringBuilder dexterities = new StringBuilder();
        // StringBuilder intelligences = new StringBuilder();
            
        for (Player player : players) {
            if (player.getCards().isEmpty()){
                continue;
            }
                Card card = player.getTopCard();
                // names.append(String.format("%10s", "Name: " + player.getName() + "   "));
                // cardNames.append(String.format("%10s", "Name of card: " +card.getName() + "   "));
                // strengths.append("Strength: " + card.getStrength() + "   ");
                // melees.append("Melee: " + card.getMelee() + "   ");
                // magics.append("Magic: " + card.getMagic() + "   ");
                // dexterities.append("Dexterity: " + card.getDexterity() + "   ");
                // intelligences.append("Intelligence: " + card.getIntelligence() + "   "); 

                names.add(player.getName());
                cardNames.add(card.getName());
                strengths.add(Integer.toString(card.getStrength()));
                melees.add(Integer.toString(card.getMelee()));
                magics.add(Integer.toString(card.getMagic()));
                dexterities.add(Integer.toString(card.getDexterity()));
                intelligences.add(Integer.toString(card.getIntelligence()));
            }

        table[0] = names.toArray();
        table[1] = cardNames.toArray();
        table[2] = strengths.toArray();
        table[3] = melees.toArray();
        table[4] = magics.toArray();
        table[5] = dexterities.toArray();
        table[6] = intelligences.toArray();

        
        for (final Object[] row : table) {
            for (Object str : row) {
                // System.out.print((String) str);
                System.out.format("%15s", str);
            }
            System.out.println();
            // System.out.format("%15s%15s%15s%15s%15s%15s%15s\n", row);
        }

    }

    public int getAttribute() {
        print("Enter attribute number: ");
        int attrNumber = getNumericInput();
        while ((attrNumber < 1 || attrNumber > 5)) {
            println("Number must be in range 1-5");
            attrNumber = getNumericInput();
        }
        clearScreen();
        return attrNumber;
    }

    public void printAttributes() {
        println("Available attributes: \n" + "1. Strength\n" + "2. Melee\n" + "3. Magic\n" + "4. Dexterity\n"
                + "5. Intelligence");
    }

    public void printMenu() {
        println("Menu: \n" + "1. Start game\n" + "2. Add new card\n" + "3. Edit card\n" + "4. Delete card\n"
                + "5. Exit\n");
    }

    public void printResultMessage(String message) {
        println(message);
    }

    public int getNumberOfPlayers(){
        int minNumberOfPlayers = 2;
        int maxNumberOfPlayers = 4;
        print("Type number of players: ");
        int choice = getNumericInput();
        while(choice < minNumberOfPlayers || choice > maxNumberOfPlayers){
            print("There can be " + minNumberOfPlayers + "-" + maxNumberOfPlayers + " players! Try again: ");
            choice = getNumericInput();
        }    
        return choice;
    }

    public void printWinningMessage(String winner, List<Player> players){
        println(winner + " wins!\n");
        for (Player player : players) {
             println(player.getName() + " ended with " + player.getCards().size() + " cards.");
        }
        println("\n");
        
    }

}