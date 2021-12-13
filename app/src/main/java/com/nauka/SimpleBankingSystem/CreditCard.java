package com.nauka.SimpleBankingSystem;

import java.util.Objects;
import java.util.Random;

public class CreditCard {
    private final String bin = "400000";
    private final String accountIdentifier;
    private final String checkDigit;
    private String pin;
    private String accountNumber;
    private int balance;
    private boolean logged = false;

    public CreditCard() {
        Random ran = new Random();
        this.accountIdentifier = String.valueOf(111111111 + ran.nextInt(888888888));
        this.checkDigit = String.valueOf(setCheckDigit());
        this.pin = String.valueOf(1111 + ran.nextInt(8888));
        this.accountNumber = bin + accountIdentifier + checkDigit;
    }

    public String getPin() {
        return pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setPin(String pin) {
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

    private int setCheckDigit() {
        String accountNumberWithoutLastDigit = bin + accountIdentifier;
        return calculateCheckDigit(accountNumberWithoutLastDigit);
    }

    public int calculateCheckDigit(String accountNumberWithoutLastDigit) {
        char[] numbersChar = accountNumberWithoutLastDigit.toCharArray();
        int[] numbers = new int[numbersChar.length];

        for (int i = 0; i < numbersChar.length; i++) {
            numbers[i] = Integer.parseInt(String.valueOf(numbersChar[i]));
        }

        int sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (i % 2 == 0) {
                numbers[i] = 2 * numbers[i];
            }
            if (numbers[i] > 9) {
                numbers[i] = numbers[i] - 9;
            }
            sum += numbers[i];
        }

        if (sum % 10 == 0) {
            return 0;
        } else {
            return 10 - (sum % 10);
        }
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (!Objects.equals(pin, that.pin)) return false;
        return Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        int result = pin != null ? pin.hashCode() : 0;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        return result;
    }

}
