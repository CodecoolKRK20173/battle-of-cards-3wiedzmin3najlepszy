package com.codecool.battleofcards.views;

import java.util.Scanner;

import com.codecool.battleofcards.services.Card;

public class GameView extends View {

    public String getUserName() {
        println("Enter user Name:");
        String userName = scanner.nextLine();
        return userName;
    }

    public void showCard(Card card) {
        println("Your card:" + card.getName());
        println("Statistics: \n" + "Strength: " + card.getStrength() + "\n" + "Melee: " + card.getMelee() + "\n"
                + "Magic: " + card.getMagic() + "\n" + "Dexterity: " + card.getDexterity() + "\n" + "Intelligence: "
                + card.getIntelligence() + "\n");
    }

    public int getAttribute() {
        int attrNumber = getNumericInput();
        print("Enter attribute number: ");
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
        println(message);
    }

}