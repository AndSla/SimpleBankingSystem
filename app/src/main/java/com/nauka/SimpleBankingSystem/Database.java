package com.nauka.SimpleBankingSystem;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private final String name;

    public Database(String name) {
        this.name = name;
        connect();
    }

    private void connect() {
        String url = "jdbc:sqlite:" + name;
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {

                System.out.println("Connection is valid.");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}
