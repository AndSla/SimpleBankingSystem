package com.nauka;

import java.util.Objects;
import java.util.Random;

public class CreditCard {
    private final String bin = "400000";
    private final String accountIdentifier;
    private final String checkDigit;
    private String pin;
    private String accountNumber;
    private int balance;

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
        String accountNumberWoCheckDigit = bin + accountIdentifier;
        char[] numbers = accountNumberWoCheckDigit.toCharArray();
        int sum = 0;
        int checkDigit = 0;

        for (char number : numbers) {
            sum += Integer.parseInt(String.valueOf(number));
        }

        while (sum % 10 != 0) {
            sum += 1;
            checkDigit += 1;
        }

        return checkDigit;

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
