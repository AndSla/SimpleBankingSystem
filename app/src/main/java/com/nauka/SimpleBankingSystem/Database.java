package com.nauka.SimpleBankingSystem;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private final SQLiteDataSource dataSource = new SQLiteDataSource();

    public Database(String name) {
        String url = "jdbc:sqlite:" + name;
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {

                String create = "CREATE TABLE IF NOT EXISTS card (" +
                        "id INTEGER PRIMARY KEY," +
                        "number VARCHAR(16) NOT NULL," +
                        "pin VARCHAR(4) NOT NULL," +
                        "balance INTEGER DEFAULT 0);";

                try (PreparedStatement preparedStatement = con.prepareStatement(create)) {
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void insert(CreditCard card) {

        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {

                String insert = "INSERT INTO card (number, pin, balance) VALUES (?, ?, ?);";

                try (PreparedStatement preparedStatement = con.prepareStatement(insert)) {
                    preparedStatement.setString(1, card.getAccountNumber());
                    preparedStatement.setString(2, card.getPin());
                    preparedStatement.setInt(3, card.getBalance());
                    preparedStatement.executeUpdate();

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

                String find = "SELECT number, pin FROM card WHERE number = ? AND pin = ?;";

                try (PreparedStatement preparedStatement = con.prepareStatement(find)) {
                    preparedStatement.setString(1, card.getAccountNumber());
                    preparedStatement.setString(2, card.getPin());

                    try (ResultSet storedCard = preparedStatement.executeQuery()) {

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

                String select = "SELECT balance FROM card WHERE number = ? AND pin = ?;";

                try (PreparedStatement preparedStatement = con.prepareStatement(select)) {
                    preparedStatement.setString(1, card.getAccountNumber());
                    preparedStatement.setString(2, card.getPin());

                    try (ResultSet storedCard = preparedStatement.executeQuery()) {

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

                String update = "UPDATE card SET balance = balance + ? WHERE number = ?;";

                try (PreparedStatement preparedStatement = con.prepareStatement(update)) {
                    preparedStatement.setInt(1, amount);
                    preparedStatement.setString(2, card.getAccountNumber());
                    preparedStatement.executeUpdate();

                    System.out.println("Income was added!\n");

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    boolean accountExists(String accountNumber) {
        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {

                String select = "SELECT number FROM card WHERE number = ?;";

                try (PreparedStatement preparedStatement = con.prepareStatement(select)) {
                    preparedStatement.setString(1, accountNumber);

                    try (ResultSet storedCard = preparedStatement.executeQuery()) {

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

        System.out.println("Such a card does not exist.\n");
        return false;
    }

    void doTransfer(CreditCard card, String transferAccountNumber, int transferAmount) {
        boolean source = false;
        boolean destination = false;

        if (getBalance(card) - transferAmount > 0) {

            try (Connection con = dataSource.getConnection()) {
                if (con.isValid(5)) {

                    String updateSource = "UPDATE card SET balance = balance - ? WHERE number = ?;";
                    String updateDestination = "UPDATE card SET balance = balance + ? WHERE number = ?;";

                    try (PreparedStatement preparedStatement = con.prepareStatement(updateSource)) {
                        preparedStatement.setInt(1, transferAmount);
                        preparedStatement.setString(2, card.getAccountNumber());
                        preparedStatement.executeUpdate();

                        source = true;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    try (PreparedStatement preparedStatement = con.prepareStatement(updateDestination)) {
                        preparedStatement.setInt(1, transferAmount);
                        preparedStatement.setString(2, transferAccountNumber);
                        preparedStatement.executeUpdate();

                        destination = true;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (source && destination) {
                        System.out.println("Success!\n");
                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Not enough money!\n");
        }

    }

    void closeAccount(CreditCard card) {

        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {

                String delete = "DELETE FROM card WHERE number = ?;";

                try (PreparedStatement preparedStatement = con.prepareStatement(delete)) {
                    preparedStatement.setString(1, card.getAccountNumber());
                    preparedStatement.executeUpdate();

                    System.out.println("\nThe account has been closed!\n");

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
