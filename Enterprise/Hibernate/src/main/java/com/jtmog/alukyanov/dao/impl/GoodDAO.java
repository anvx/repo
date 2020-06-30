package com.jtmog.alukyanov.dao.impl;

import com.jtmog.alukyanov.dao.GlobalDAO;
import com.jtmog.alukyanov.entity.Good;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodDAO implements GlobalDAO<Good, String> {
    private SessionFactory sessionFactory;

    public GoodDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Good model) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(model);
    }

    public Good read(long goodId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Good.class, goodId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Good> read(String title) {
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from Good where title = :title");
        query.setParameter("title", title);
        return (List<Good>) query.list();
    }

    @Override
    public void update(Good model) {
        Session session = sessionFactory.getCurrentSession();
        session.update(model);
    }

    @Override
    public void delete(Good model) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(model);
    }

    @SuppressWarnings("unchecked")
    public List<Good> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Good>) session.createQuery("from Good").list();
    }
}