package com.nauka.SimpleBankingSystem;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        Bank bank = new Bank();
        CreditCard loggedCard = null;

        String dbName = ui.getDbNameFromCmdLineParameter(args[0], args[1]);
        if (dbName != null) {
            Database db = new Database(dbName);
            bank.setCards(db);
        }

        while (ui.isRunning()) {
            ui.showMainMenu();
            ui.getMenuItemFromInput(2);

            switch (ui.getMenuItem()) {
                case 1:
                    CreditCard newCard = bank.createCard();
                    ui.showInfo(newCard.getAccountNumber(), newCard.getPin());
                    break;
                case 2:
                    loggedCard = bank.logIn(ui.loginData());
                    break;
                case 0:
                    ui.exit();
            }

            if (loggedCard != null) {
                while (loggedCard.isLogged()) {
                    ui.showClientMenu();
                    ui.getMenuItemFromInput(2);

                    switch (ui.getMenuItem()) {
                        case 1:
                            ui.showBalance(loggedCard.getBalance());
                            break;
                        case 2:
                            loggedCard.setLogged(false);
                            ui.showLogoutMessage();
                            break;
                        case 0:
                            loggedCard.setLogged(false);
                            ui.exit();
                    }

                }
            }

        }

    }

}