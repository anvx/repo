package com.jtmog.alukyanov.dao.impl;

import com.jtmog.alukyanov.dao.GlobalDAO;
import com.jtmog.alukyanov.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO implements GlobalDAO<Order, Long> {

    private Connection connection;
    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String TOTAL_PRICE = "total_price";

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Order model) {
        boolean result = false;

        String sql = "INSERT INTO bucket(user_id) VALUES(?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, model.getUserId());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Order read(Long userId) {
        final Order result = new Order();
        result.setId(-1);

        String sql = "SELECT * FROM bucket WHERE user_id = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result.setId(resultSet.getLong(ID));
                    result.setUserId(resultSet.getLong(USER_ID));
                    result.setTotalPrice(resultSet.getLong(TOTAL_PRICE));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(Order model) {
        String sql = "UPDATE bucket SET total_price = (?) WHERE user_id = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, model.getTotalPrice());
            statement.setLong(2, model.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Order model) {
        boolean result = false;

        String sql = "DELETE FROM bucket WHERE id = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, model.getId());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
