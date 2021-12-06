package com.nauka.SimpleBankingSystem;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        UserInterface ui = new UserInterface();
        Database db = new Database("card.db");
        Client loggedClient = null;

        while (ui.isRunning()) {
            ui.showMainMenu();
            ui.getMenuItemFromInput(2);

            switch (ui.getMenuItem()) {
                case 1:
                    Client newClient = bank.createClient();
                    ui.showInfo(newClient.getCard().getAccountNumber(), newClient.getCard().getPin());
                    break;
                case 2:
                    loggedClient = bank.logIn(ui.loginData());
                    break;
                case 0:
                    ui.exit();
            }

            if (loggedClient != null) {
                while (loggedClient.isLogged()) {
                    ui.showClientMenu();
                    ui.getMenuItemFromInput(2);

                    switch (ui.getMenuItem()) {
                        case 1:
                            ui.showBalance(loggedClient.getCard().getBalance());
                            break;
                        case 2:
                            loggedClient.setLogged(false);
                            ui.showLogoutMessage();
                            break;
                        case 0:
                            loggedClient.setLogged(false);
                            ui.exit();
                    }

                }
            }

        }

    }

}