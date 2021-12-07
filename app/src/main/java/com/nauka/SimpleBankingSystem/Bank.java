package com.nauka.SimpleBankingSystem;

public class Bank {

    private Database cards;

    public Bank() {
    }

    public void setCards(Database cards) {
        this.cards = cards;
    }

    public CreditCard createCard() {
        CreditCard card = new CreditCard();
        cards.insert(card);
        return card;
    }

    public CreditCard logIn(CreditCard loginCard) {
            if (cards.find(loginCard)) {
                loginCard.setLogged(true);
                System.out.println("You have successfully logged in!\n");
                return loginCard;
            }

        System.out.println("Wrong card number or PIN!\n");
        return null;

    }

}
