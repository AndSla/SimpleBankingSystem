package com.nauka.SimpleBankingSystem;

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

    void showCardMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    void showAddIncomePrompt() {
        System.out.println("\nEnter income:");
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

    String getDbNameFromCmdLineParameter(String param1, String param2) {
        if (param1.equals("-fileName")) {
            return param2;
        } else {
            System.out.println("Wrong parameters! Try again.");
            setRunning(false);
        }
        return null;
    }

    int getIncomeValue() {
        int value = Integer.parseInt(sc.nextLine());
        System.out.println();
        return value;
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
