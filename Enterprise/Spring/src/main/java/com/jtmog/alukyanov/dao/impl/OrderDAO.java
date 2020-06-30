package com.jtmog.alukyanov.dao.impl;

import com.jtmog.alukyanov.dao.GlobalDAO;
import com.jtmog.alukyanov.entity.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderDAO implements GlobalDAO<Order, Long> {

    private final JdbcTemplate jdbcTemplate;

    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insert(Order model) {

        String sql = "INSERT INTO bucket(user_id, total_price) VALUES(?, ?);";
        int columnUpdated = jdbcTemplate.update(sql, model.getUserId(), model.getTotalPrice());
        return columnUpdated > 0;
    }

    @Override
    public Optional<Order> read(Long userId) {
        Optional<Order> result;

        String sql = "SELECT * FROM bucket WHERE user_id = (?);";
        try {
            result = Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    new Object[]{userId},
                    new BeanPropertyRowMapper<>(Order.class)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return result;
    }

    @Override
    public void update(Order model) {
        String sql = "UPDATE bucket SET total_price = (?) WHERE user_id = (?);";
        jdbcTemplate.update(sql, model.getTotalPrice(), model.getUserId());
    }

    @Override
    public boolean delete(Order model) {
        String sql = "DELETE FROM bucket WHERE id = (?);";
        int columnUpdated = jdbcTemplate.update(sql, model.getId());
        return columnUpdated > 0;
    }
}
