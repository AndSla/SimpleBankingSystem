package com.nauka;

import java.util.Scanner;

public class UserInterface {

    private int menuItem;
    private final Scanner sc = new Scanner(System.in);
    private boolean running = true;

    void showMainMenu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    void showClientMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    void showLogoutMessage() {
        System.out.println("\nYou have successfully logged out!\n");
    }

    void getMenuItemFromInput(int maxMenuItemNumber) {
        while (true) {
            String chosenNumber = sc.nextLine();
            if (chosenNumber.matches("[0-" + maxMenuItemNumber + "]")) {
                setMenuItem(Integer.parseInt(chosenNumber));
                break;
            } else {
                System.out.print("> ");
            }
        }
    }

    void showInfo(String accountNumber, String pin) {
        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");
        System.out.println(accountNumber);
        System.out.println("Your card PIN:");
        System.out.println(pin + "\n");
    }

    void showBalance(int balance) {
        System.out.println("\nBalance: " + balance + "\n");
    }

    CreditCard loginData() {
        System.out.println("\nEnter your card number:");
        String accountNumber = sc.nextLine();
        System.out.println("Enter your PIN:");
        String pin = sc.nextLine();
        System.out.println();

        CreditCard loginCard = new CreditCard();
        loginCard.setAccountNumber(accountNumber);
        loginCard.setPin(pin);

        return loginCard;
    }

    void exit() {
        setRunning(false);
        System.out.println("\nBye!");
    }

    public int getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(int menuItem) {
        this.menuItem = menuItem;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
