package com.jtmog.alukyanov.dao.impl;

import com.jtmog.alukyanov.dao.GlobalDAO;
import com.jtmog.alukyanov.entity.Good;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDAO implements GlobalDAO<Good, String> {

    private Connection connection;
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String PRICE = "price";

    public GoodDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Good model) {
        boolean result = false;

        String sql = "INSERT INTO good (title, price) VALUES(?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getTitle());
            statement.setLong(1, model.getPrice());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Good read(long goodId) {
        final Good result = new Good();
        result.setId(-1);

        String sql = "SELECT * FROM good WHERE id = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, goodId);
            executeReadSqlStatement(result, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Good read(String title) {
        final Good result = new Good();
        result.setId(-1);

        String sql = "SELECT * FROM good WHERE title = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            executeReadSqlStatement(result, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void executeReadSqlStatement(Good result, PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                result.setId(resultSet.getLong(ID));
                result.setTitle(resultSet.getString(TITLE));
                result.setPrice(resultSet.getLong(PRICE));
            }
        }
    }

    @Override
    public void update(Good model) {
        String sql = "UPDATE good SET title = (?), price = (?) WHERE id = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getTitle());
            statement.setLong(2, model.getPrice());
            statement.setLong(3, model.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Good model) {
        boolean result = false;

        String sql = "DELETE FROM good WHERE id = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, model.getId());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Good> getAll() {
        List<Good> result = new ArrayList<>();

        String sql = "SELECT * FROM good;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Good tempGood = new Good();
                    tempGood.setId(resultSet.getLong(ID));
                    tempGood.setTitle(resultSet.getString(TITLE));
                    tempGood.setPrice(resultSet.getLong(PRICE));

                    result.add(tempGood);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}