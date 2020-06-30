package com.jtmog.alukyanov.listener;

import com.jtmog.alukyanov.dao.ConnectionToDB;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.*;

@WebListener()
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = ConnectionToDB.openConnection();
             Statement statement = conn.createStatement()) {

            String createUserDataBase =
                    "CREATE TABLE user ( " +
                            "id INT NOT NULL auto_increment," +
                            "login VARCHAR(20) NOT NULL ," +
                            "password VARCHAR (8)," +
                            "PRIMARY KEY (id)" +
                            ");";

            String createGoodDataBase =
                    "CREATE TABLE good ( " +
                            "id INT NOT NULL auto_increment," +
                            "title VARCHAR(100) NOT NULL ," +
                            "price BIGINT," +
                            "PRIMARY KEY (id)" +
                            ");";

            String createOrderDataBase =
                    "CREATE TABLE bucket ( " +
                            "id INT NOT NULL auto_increment," +
                            "user_id INT NOT NULL," +
                            "total_price BIGINT," +
                            "FOREIGN KEY (user_id) references user(id) ON DELETE CASCADE," +
                            "PRIMARY KEY (id)" +
                            ");";

            String createOrderAndGoodDataBase =
                    "CREATE TABLE bucketAndGood ( " +
                            "id INT NOT NULL auto_increment," +
                            "order_id INT NOT NULL, " +
                            "good_id INT NOT NULL, " +
                            "FOREIGN KEY (order_id) references bucket(id)," +
                            "FOREIGN KEY (good_id) references good(id)," +
                            "PRIMARY KEY (id)" +
                            ");";

            statement.execute(createUserDataBase);
            statement.execute(createGoodDataBase);
            statement.execute(createOrderDataBase);
            statement.execute(createOrderAndGoodDataBase);

            String insertIntoGoodDataBase =
                    "INSERT INTO good(title, price) VALUES" +
                            "('Bread', 7)," +
                            "('Tomato', 12)," +
                            "('Sprite', 14)," +
                            "('Cucumber', 24);";

            statement.execute(insertIntoGoodDataBase);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
