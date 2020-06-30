package com.jtmog.alukyanov.dao.impl;

import com.jtmog.alukyanov.dao.GlobalDAO;
import com.jtmog.alukyanov.entity.Good;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GoodDAO implements GlobalDAO<Good, String> {

    private final JdbcTemplate jdbcTemplate;

    public GoodDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insert(Good model) {
        String sql = "INSERT INTO good (title, price) VALUES(?, ?);";
        int columnUpdated = jdbcTemplate.update(sql, model.getTitle(), model.getPrice());
        return columnUpdated > 0;
    }

    public Optional<Good> read(long goodId) {
        Optional<Good> result;

        String sql = "SELECT * FROM good WHERE id = (?);";
        try {
            result = Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    new Object[]{goodId},
                    new BeanPropertyRowMapper<>(Good.class)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return result;
    }

    @Override
    public Optional<Good> read(String title) {
        Optional<Good> result;

        String sql = "SELECT * FROM good WHERE title = (?);";
        try {
            result = Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    new Object[]{title},
                    new BeanPropertyRowMapper<>(Good.class)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return result;
    }

    @Override
    public void update(Good model) {
        String sql = "UPDATE good SET title = (?), price = (?) WHERE id = (?);";
        jdbcTemplate.update(sql, model.getTitle(), model.getPrice(), model.getId());
    }

    @Override
    public boolean delete(Good model) {
        String sql = "DELETE FROM good WHERE id = (?);";
        int columnUpdated = jdbcTemplate.update(sql, model.getId());
        return columnUpdated > 0;
    }

    public List<Good> getAll() {
        String sql = "SELECT * FROM good;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Good.class));
    }
}