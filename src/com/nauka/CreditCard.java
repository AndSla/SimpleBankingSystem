package com.nauka;

import java.util.Objects;
import java.util.Random;

public class CreditCard {
    private final int bin = 400000;
    private int accountIdentifier;
    private int checkDigit;
    private int pin;
    private String accountNumber;
    private int balance;

    public CreditCard() {
        Random ran = new Random();
        this.accountIdentifier = 111111111 + ran.nextInt(888888888);
        this.checkDigit = 9;
        this.pin = 1111 + ran.nextInt(8888);
        this.accountNumber = bin + "" + accountIdentifier + "" + checkDigit;
    }

    public int getPin() {
        return pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (pin != that.pin) return false;
        return Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        int result = pin;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        return result;
    }

}
