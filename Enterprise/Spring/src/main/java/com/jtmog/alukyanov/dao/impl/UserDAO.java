package com.jtmog.alukyanov.dao.impl;

import com.jtmog.alukyanov.dao.GlobalDAO;
import com.jtmog.alukyanov.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAO implements GlobalDAO<User, String> {

    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insert(User model) {
        String sql = "INSERT INTO user(login) VALUES(?);";
        int columnUpdated = jdbcTemplate.update(sql, model.getLogin());
        return columnUpdated > 0;
    }

    @Override
    public Optional<User> read(String login) {
        Optional<User> result;

        String sql = "SELECT * FROM user WHERE login = (?);";
        try {
            result = Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    new Object[]{login},
                    new BeanPropertyRowMapper<>(User.class)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return result;
    }

    @Override
    public void update(User model) {
        String sql = "UPDATE user SET login = (?) WHERE id = (?);";
        jdbcTemplate.update(sql, model.getLogin(), model.getId());
    }

    @Override
    public boolean delete(User model) {
        String sql = "DELETE FROM user WHERE id = (?);";
        int columnUpdated = jdbcTemplate.update(sql, model.getId());
        return columnUpdated > 0;
    }
}
