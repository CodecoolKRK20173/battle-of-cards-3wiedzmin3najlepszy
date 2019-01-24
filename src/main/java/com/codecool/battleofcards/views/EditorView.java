package com.codecool.battleofcards.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.codecool.battleofcards.services.Card;

public class EditorView extends View {

    private List<String> attributeNames;

    public EditorView() {
        String[] attr = {"Strength", "Melee", "Magic", "Dexterity", "Intelligence"};
        List<String> attributeNames = new ArrayList(Arrays.asList(attr));
        this.attributeNames = attributeNames;
    }

    public String getCardName() {
        println("Enter card name: ");
        clearScreen();
        return scanner.nextLine();
    }

    public List<Integer> getAttributesValues() {
        List<Integer> attributesValues = new ArrayList<>();
        for (String attrName : attributeNames) {
            println("Enter value of attribute (or nothing to omit): " + attrName);
            int attrValue = Integer.valueOf(getAttributeValue());
            attributesValues.add(attrValue);
        }
        return attributesValues;
    }

    private String getAttributeValue() {
        String value = scanner.nextLine();
        while (!(value.matches("^[1-9]+$") || value.isEmpty())) {
            println("Wrong attribute value. Try again.");
            value = scanner.nextLine();
        }
        if (value.matches("^[1-9]+$")) {
            clearScreen();
            return value;
        } else {
            clearScreen();
            return "-1";
        }
    }

    public void printListOfCards(List<Card> cards) {
        System.out.println("List of cards:\n");
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            println((i + 1) + ". " + card.getName());
        }
    }

    public int getCardId() {
        println("\nEnter card Id: ");
        return getNumericInput();
    }

}