package com.jtmog.alukyanov.listener;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Service
public class ApplicationListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(ApplicationListener.class);

    private Connection connection;
    private Statement statement;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try (InputStream in = ApplicationListener.class.getClassLoader().
                getResourceAsStream("h2.properties")) {

            Class.forName("org.h2.Driver");

            Properties properties = new Properties();
            properties.load(in);
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(
                    url,
                    user,
                    password
            );
            statement = connection.createStatement();

            String createUserDataBase =
                    "CREATE TABLE IF NOT EXISTS user ( " +
                            "id INT NOT NULL auto_increment," +
                            "login VARCHAR(20) UNIQUE NOT NULL ," +
                            "password VARCHAR (8)," +
                            "PRIMARY KEY (id)" +
                            ");";

            String createGoodDataBase =
                    "CREATE TABLE IF NOT EXISTS good ( " +
                            "id INT NOT NULL auto_increment," +
                            "title VARCHAR(100) NOT NULL ," +
                            "price BIGINT," +
                            "PRIMARY KEY (id)" +
                            ");";

            String createOrderDataBase =
                    "CREATE TABLE IF NOT EXISTS bucket ( " +
                            "id INT NOT NULL auto_increment," +
                            "user_id INT UNIQUE NOT NULL," +
                            "total_price BIGINT," +
                            "FOREIGN KEY (user_id) references user(id) ON DELETE CASCADE," +
                            "PRIMARY KEY (id)" +
                            ");";

            String createOrderAndGoodDataBase =
                    "CREATE TABLE IF NOT EXISTS bucketAndGood ( " +
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
                    "MERGE INTO good(title, price) KEY(title) VALUES" +
                            "('Bread', 7)," +
                            "('Tomato', 12)," +
                            "('Sprite', 14)," +
                            "('Cucumber', 24);";

            String testUser =
                    "MERGE INTO user(login, password) KEY(login) VALUES" +
                            "('User1', 1)," +
                            "('User2', 2); ";

            statement.execute(insertIntoGoodDataBase);
            statement.execute(testUser);
        } catch (Exception e1) {
            logger.info("ApplicationListener failed");
            e1.printStackTrace();

        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                logger.info("ApplicationListener failed");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
