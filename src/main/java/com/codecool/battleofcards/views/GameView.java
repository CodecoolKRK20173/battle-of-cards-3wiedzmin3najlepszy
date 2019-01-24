package com.codecool.battleofcards.views;

import java.util.Scanner;
import java.util.List;

import com.codecool.battleofcards.services.Card;
import com.codecool.battleofcards.services.Player;
import java.lang.StringBuilder;
import java.util.ArrayList;

public class GameView extends View {

    private static final String green = "\u001B[32m";
    private static final String purple = "\u001B[34m";
    private static final String defaultColor = "\033[0m";

    public String getUserName() {
        print("Enter user Name: ");
        String userName = scanner.nextLine();
        return userName;
    }

    public void showCard(Card card, String name) {
        println(purple + "\n" + name + "\'s" + " turn\n" + defaultColor);
        println(green + "Your card: " + card.getName());
        println(green + "Statistics: \n\n" + "(1) Strength: " + card.getStrength() + "\n" + "(2) Melee: " + card.getMelee() + "\n"
                + "(3) Magic: " + card.getMagic() + "\n" + "(4) Dexterity: " + card.getDexterity() + "\n" + "(5) Intelligence: "
                + card.getIntelligence() + "\n" + defaultColor);
    }

    public void showPlayersTopCards(List<Player> players, int attribute){
        int numberOfRows = 7;
        final Object[][] table = new Object[numberOfRows][];
        List<String> names = new ArrayList<>();
        List<String> cardNames = new ArrayList<>();
        List<String> strengths = new ArrayList<>();
        List<String> melees = new ArrayList<>();
        List<String> magics = new ArrayList<>();
        List<String> dexterities = new ArrayList<>();
        List<String> intelligences = new ArrayList<>();
       
        for (Player player : players) {
            if (player.getCards().isEmpty()){
                continue;
            }
                Card card = player.getTopCard();
                names.add("Name: " + player.getName());
                cardNames.add("Card name: " + card.getName());
                strengths.add("Strength: " + Integer.toString(card.getStrength()));
                melees.add("Melee: " + Integer.toString(card.getMelee()));
                magics.add("Magic: " + Integer.toString(card.getMagic()));
                dexterities.add("Dexterity: " + Integer.toString(card.getDexterity()));
                intelligences.add("Intelligence: " + Integer.toString(card.getIntelligence()));
            }

            table[0] = names.toArray();
            table[1] = cardNames.toArray();
            table[2] = strengths.toArray();
            table[3] = melees.toArray();
            table[4] = magics.toArray();
            table[5] = dexterities.toArray();
            table[6] = intelligences.toArray();

            println("\nThese are cards of all players in this round:\n");
            int numberOfIteration = 0;

            for (final Object[] row : table) {
                for (Object str : row) {
                    if(attribute+1 == numberOfIteration){
                        System.out.format(purple + "%-30s" + defaultColor, str);
                    }
                    else{
                        System.out.format("%-30s", str); 
                    }
                }
                numberOfIteration++;
                System.out.println();
            }
        }


    public int getAttribute() {
        print("Enter attribute number: ");
        int attrNumber = getNumericInput();
        while ((attrNumber < 1 || attrNumber > 5)) {
            println("Number must be in range 1-5");
            attrNumber = getNumericInput();
        }
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
        println(purple + "\n" + message + "\n" + defaultColor);
    }

    public int getNumberOfPlayers() {
        int minNumberOfPlayers = 2;
        int maxNumberOfPlayers = 4;
        print("Type number of players: ");
        int choice = getNumericInput();
        while (choice < minNumberOfPlayers || choice > maxNumberOfPlayers) {
            print("There can be " + minNumberOfPlayers + "-" + maxNumberOfPlayers + " players! Try again: ");
            choice = getNumericInput();
        }
        return choice;
    }

    public void printWinningMessage(String winner, List<Player> players) {
        println(purple + winner + " wins!\n");
        for (Player player : players){
            if(player.getCards().size()==0){
                println(purple + player.getName() + " has run out of cards!");
            }
        }
        for (Player player : players) {
            println(defaultColor + player.getName() + " ended with " + player.getCards().size() + " cards.");
        }
        println("\n");

    }

}