package com.jtmog.alukyanov.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB implements AutoCloseable {
    private static Connection connection;

    public static Connection openConnection() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:h2:mem:hw18;DB_CLOSE_DELAY=-1",
                    "sa",
                    ""
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
