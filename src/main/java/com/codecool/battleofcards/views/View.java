package com.codecool.battleofcards.views;

import java.util.Scanner;

abstract class View {

    protected Scanner scanner = new Scanner(System.in);

    public void print(String message) {
        System.out.print(message);
    }

    public void println(String message) {
        System.out.println(message);
    }

    public int getNumericInput() {
        while (!scanner.hasNextInt()) {
            println("Input must be an Integer. Try again.");
            scanner.nextLine();
        }
        int number = scanner.nextInt();
        scanner.nextLine();
        // clearScreen();
        return number;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String getEmptyInput() {
        println("Press enter to continue: ");
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            clearScreen();
            return "-1";
        }
        clearScreen();
        return "-1";

    }
}