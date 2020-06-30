package com.jtmog.alukyanov.dao.impl;

import com.jtmog.alukyanov.dao.GlobalDAO;
import com.jtmog.alukyanov.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO implements GlobalDAO<User, String> {
    private SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(User model) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(model);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> read(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from User where login = :login");
        query.setParameter("login", login);
        return (List<User>) query.list();
    }

    @Override
    public void update(User model) {
        Session session = sessionFactory.getCurrentSession();
        session.update(model);
    }

    @Override
    public void delete(User model) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(model);
    }
}
