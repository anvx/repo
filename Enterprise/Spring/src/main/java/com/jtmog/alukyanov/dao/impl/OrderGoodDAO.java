package com.jtmog.alukyanov.dao.impl;

import com.jtmog.alukyanov.dao.GlobalDAO;
import com.jtmog.alukyanov.entity.OrderGood;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderGoodDAO implements GlobalDAO<OrderGood, Long> {

    private JdbcTemplate jdbcTemplate;

    public OrderGoodDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insert(OrderGood model) {
        String sql = "INSERT INTO bucketAndGood(order_id, good_id) VALUES(?, ?);";
        int columnUpdated = jdbcTemplate.update(sql, model.getOrderId(), model.getGoodId());
        return columnUpdated > 0;
    }

    @Override
    public Optional<OrderGood> read(Long orderId) {
        Optional<OrderGood> result;

        String sql = "SELECT * FROM bucketAndGood WHERE order_id = (?);";
        try {
            result = Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    new Object[]{orderId},
                    new BeanPropertyRowMapper<>(OrderGood.class)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return result;
    }

    @Override
    public void update(OrderGood model) {
        throw new UnsupportedOperationException("You can't UPDATE.");
    }

    @Override
    public boolean delete(OrderGood model) {
        String sql = "DELETE FROM bucketAndGood WHERE order_id = (?);";
        int columnUpdated = jdbcTemplate.update(sql, model.getId());
        return columnUpdated > 0;
    }

    public List<OrderGood> getAll() {
        String sql = "SELECT * FROM bucketAndGood;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderGood.class));
    }

    public List<OrderGood> getAllByOrderId(long orderId) {
        String sql = "SELECT * FROM bucketAndGood WHERE order_id = (?);";
        return jdbcTemplate.query(sql, new Object[]{orderId}, new BeanPropertyRowMapper<>(OrderGood.class));
    }
}
