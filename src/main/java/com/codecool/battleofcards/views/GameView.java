package com.codecool.battleofcards.views;

import java.util.Scanner;
import java.util.List;

import com.codecool.battleofcards.services.Card;
import com.codecool.battleofcards.services.Player;

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