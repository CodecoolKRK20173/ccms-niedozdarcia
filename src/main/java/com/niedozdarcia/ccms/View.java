package com.niedozdarcia.ccms;

import java.util.Scanner;

public class View {

    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public String getInputString(String text) {
        System.out.print(text);
        return scanner.nextLine();
    }

    public int getInputInt(String text) {
        System.out.print(text);
        int input = -1;


        while (input < 0) {

            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid parameter");
            }
        }

        return input;
    }

    public void print(String text) {
        System.out.println(text);
    }

    public void printMenu(String... items) {

        System.out.println("What would you like to do: ");

        for (int i=0; i<items.length; i++) {
            System.out.printf("    (%d) %s", i, items[i]);
            i++;
        }

        System.out.printf("    (0)%s", items[0]);
    }
}
