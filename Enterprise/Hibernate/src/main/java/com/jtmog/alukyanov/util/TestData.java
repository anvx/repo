package com.jtmog.alukyanov.util;

import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestData {

    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(TestData.class);

    public TestData(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void inputTestData() {

        Session session = sessionFactory.getCurrentSession();
        List<Good> goods = new ArrayList<>();
        goods.add(new Good("Milk", 1));
        goods.add(new Good("Cucumber", 2));
        goods.add(new Good("Bread", 3));

        for (Good good : goods) {
            session.save(good);
        }

        session.save(new User("User1", "1"));
    }
}
