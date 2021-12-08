package com.nauka.SimpleBankingSystem;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private final SQLiteDataSource dataSource = new SQLiteDataSource();

    public Database(String name) {
        String url = "jdbc:sqlite:" + name;
        dataSource.setUrl(url);
    }

    void insert(CreditCard card) {

        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {

                try (Statement statement = con.createStatement()) {
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS card (" +
                            "id INTEGER PRIMARY KEY," +
                            "number VARCHAR(16) NOT NULL," +
                            "pin VARCHAR(4) NOT NULL," +
                            "balance INTEGER DEFAULT 0);");

                    statement.executeUpdate("INSERT INTO card (number, pin, balance) VALUES " +
                            "(" + card.getAccountNumber() + ", " + card.getPin() + ", " + card.getBalance() + ");");

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    boolean find(CreditCard card) {

        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {

                try (Statement statement = con.createStatement()) {

                    try (ResultSet storedCard = statement.executeQuery("SELECT number, pin " +
                            "FROM card " +
                            "WHERE " +
                            "number = '" + card.getAccountNumber() + "'" +
                            " AND " +
                            "pin = '" + card.getPin() + "';")) {

                        if (storedCard.next()) {
                            return true;
                        }

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    int getBalance(CreditCard card) {

        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {

                try (Statement statement = con.createStatement()) {

                    try (ResultSet storedCard = statement.executeQuery("SELECT balance " +
                            "FROM card " +
                            "WHERE " +
                            "number = '" + card.getAccountNumber() + "'" +
                            " AND " +
                            "pin = '" + card.getPin() + "';")) {

                        return storedCard.getInt("balance");

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    }

    void addIncome(CreditCard card, int amount) {
        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {

                try (Statement statement = con.createStatement()) {

                    statement.executeUpdate("UPDATE card " +
                            "SET balance = balance + " + amount + " " +
                            "WHERE number = " + card.getAccountNumber() + ";");

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
