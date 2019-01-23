package com.codecool.battleofcards.views;

import java.util.Scanner;
import java.util.List;

import com.codecool.battleofcards.services.Card;
import com.codecool.battleofcards.services.Player;

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
        println(winner + " wins!\n");
        for (Player player : players) {
            println(player.getName() + " ended with " + player.getCards().size() + " cards.");
        }
        println("\n");

    }

}