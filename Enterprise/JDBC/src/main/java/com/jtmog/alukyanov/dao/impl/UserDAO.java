package com.jtmog.alukyanov.dao.impl;

import com.jtmog.alukyanov.dao.GlobalDAO;
import com.jtmog.alukyanov.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements GlobalDAO<User, String> {

    private Connection connection;
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(User model) {
        boolean result = false;

        String sql = "INSERT INTO user(login) VALUES(?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getLogin());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User read(String login) {
        final User result = new User();
        result.setId(-1);

        String sql = "SELECT * FROM user WHERE login = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result.setId(resultSet.getLong(ID));
                    result.setLogin(resultSet.getString(LOGIN));
                    result.setPassword(resultSet.getString(PASSWORD));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(User model) {
        String sql = "UPDATE user SET login = (?) WHERE id = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getLogin());
            statement.setLong(2, model.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(User model) {
        boolean result = false;

        String sql = "DELETE FROM user WHERE id = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, model.getId());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
