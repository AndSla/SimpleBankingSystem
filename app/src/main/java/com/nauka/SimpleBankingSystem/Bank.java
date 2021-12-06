package com.nauka.SimpleBankingSystem;

import java.util.HashSet;
import java.util.Set;

public class Bank {

    private final Set<Client> clients = new HashSet<>();

    public Client createClient() {
        Client client = new Client();
        clients.add(client);
        return client;
    }

    public Client logIn(CreditCard loginCard) {
        for (Client client : clients) {
            if (client.getCard().equals(loginCard)) {
                client.setLogged(true);
                System.out.println("You have successfully logged in!\n");
                return client;
            }
        }

        System.out.println("Wrong card number or PIN!\n");
        return null;

    }

}
