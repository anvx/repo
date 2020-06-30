package com.jtmog.alukyanov.dao.impl;

import com.jtmog.alukyanov.dao.GlobalDAO;
import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAO implements GlobalDAO<Order, Long> {
    private SessionFactory sessionFactory;

    public OrderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Order model) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(model);
    }

    @SuppressWarnings("unchecked")
    public List<Order> readByUserId(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from Bucket where user.id = :userId");
        query.setParameter("userId", userId);
        return (List<Order>) query.list();
    }

    @Override
    public void update(Order model) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Bucket SET totalPrice =: price WHERE id =: id");
        query.setParameter("price", model.getTotalPrice());
        query.setParameter("id", model.getId());
        query.executeUpdate();
    }

    @Override
    public void delete(Order model) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(model);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> read(Long orderId) {
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from Bucket where id = :orderId");
        query.setParameter("orderId", orderId);
        return (List<Order>) query.list();
    }
}
