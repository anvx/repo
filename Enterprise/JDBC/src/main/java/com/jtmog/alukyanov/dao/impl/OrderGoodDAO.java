package com.jtmog.alukyanov.dao.impl;

import com.jtmog.alukyanov.dao.GlobalDAO;
import com.jtmog.alukyanov.entity.OrderGood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderGoodDAO implements GlobalDAO<OrderGood, Long> {

    private Connection connection;
    private static final String ID = "id";
    private static final String ORDER_ID = "order_id";
    private static final String GOOD_ID = "good_id";

    public OrderGoodDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(OrderGood model) {
        boolean result = false;

        String sql = "INSERT INTO bucketAndGood(order_id, good_id) VALUES(?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, model.getOrderId());
            statement.setLong(2, model.getGoodId());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public OrderGood read(Long orderId) {
        final OrderGood result = new OrderGood();
        result.setId(-1);

        String sql = "SELECT * FROM bucketAndGood WHERE order_id = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result.setId(resultSet.getLong(ID));
                    result.setOrderId(resultSet.getLong(ORDER_ID));
                    result.setGoodId(resultSet.getLong(GOOD_ID));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(OrderGood model) {
        throw new UnsupportedOperationException("You can't UPDATE.");
    }

    @Override
    public boolean delete(OrderGood model) {
        boolean result = false;

        String sql = "DELETE FROM bucketAndGood WHERE order_id = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, model.getOrderId());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<OrderGood> getAll() {
        List<OrderGood> result = new ArrayList<>();

        String sql = "SELECT * FROM bucketAndGood;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                addOrderGoodToArray(result, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<OrderGood> getAllByOrderId(long orderId) {
        List<OrderGood> result = new ArrayList<>();

        String sql = "SELECT * FROM bucketAndGood WHERE order_id = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                addOrderGoodToArray(result, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void addOrderGoodToArray(List<OrderGood> result, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            OrderGood tempGood = new OrderGood();
            tempGood.setId(resultSet.getLong(ID));
            tempGood.setOrderId(resultSet.getLong(ORDER_ID));
            tempGood.setGoodId(resultSet.getLong(GOOD_ID));

            result.add(tempGood);
        }
    }
}
