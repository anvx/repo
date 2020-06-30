package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.dao.ConnectionToDB;
import com.jtmog.alukyanov.dao.impl.OrderDAO;
import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.Order;
import com.jtmog.alukyanov.servise.OrderServise;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderServise {
    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    private static final String BUCKET = "bucket";
    private static final String USER_ID = "userId";
    private static final String TOTAL_PRICE = "totalPrice";

    @Override
    public boolean createOrder(long userId) {
        try (Connection connection = ConnectionToDB.openConnection()) {
            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = new Order(userId);
            orderDAO.insert(order);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Order getOrderByUserId(long userId) {
        Order order = null;
        try (Connection connection = ConnectionToDB.openConnection()) {
            OrderDAO orderDAO = new OrderDAO(connection);
            order = orderDAO.read(userId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean updateOrder(long userId, long totalPrice) {
        Order order = new Order();
        try (Connection connection = ConnectionToDB.openConnection()) {
            OrderDAO orderDAO = new OrderDAO(connection);
            order.setUserId(userId);
            order.setTotalPrice(totalPrice);
            orderDAO.update(order);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void updateTotalPriceInOrder(HttpServletRequest req) {
        List<Good> listGoodByOrderGood = (List<Good>) req.getAttribute(BUCKET);
        long totalPrice = 0;
        for (Good good : listGoodByOrderGood) {
            totalPrice += good.getPrice();
        }
        OrderServise orderServise = new OrderServiceImpl();

        Long userId = (Long) req.getSession().getAttribute(USER_ID);

        orderServise.updateOrder(userId, totalPrice);
    }

    @Override
    public void readTotalPriceFromOrderDB(HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        OrderServise orderServise = new OrderServiceImpl();
        Order orderByUserId = orderServise.getOrderByUserId(userId);
        long totalPrice = orderByUserId.getTotalPrice();
        req.setAttribute(TOTAL_PRICE, totalPrice);
    }
}
