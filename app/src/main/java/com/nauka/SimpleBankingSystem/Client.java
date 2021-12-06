package com.nauka.SimpleBankingSystem;

public class Client {

    private final CreditCard card = new CreditCard();
    private boolean logged = false;

    public CreditCard getCard() {
        return card;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }

}
