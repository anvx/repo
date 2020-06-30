package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.dao.ConnectionToDB;
import com.jtmog.alukyanov.dao.impl.UserDAO;
import com.jtmog.alukyanov.entity.Order;
import com.jtmog.alukyanov.entity.User;
import com.jtmog.alukyanov.servise.OrderServise;
import com.jtmog.alukyanov.servise.UserServise;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class UserServiseImpl implements UserServise {
    private static final Logger logger = Logger.getLogger(UserServiseImpl.class);

    private static final String USER_ID = "userId";
    private static final String ORDER_ID = "orderId";

    @Override
    public boolean createUser(String login) {
        try (Connection connection = ConnectionToDB.openConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            User user = new User(login);
            userDAO.insert(user);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createUser(String login, String password) {
        throw new UnsupportedOperationException("Not yet.");
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try (Connection connection = ConnectionToDB.openConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            user = userDAO.read(login);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void createUser(HttpServletRequest req, String clientName, String license) {
        HttpSession session = req.getSession();

        String login = req.getParameter(clientName);

        UserServise userServise = new UserServiseImpl();
        User userByLogin = userServise.getUserByLogin(login);
        if (userByLogin.getId() < 0) {
            userServise.createUser(login);
            userByLogin = userServise.getUserByLogin(login);
        }
        session.setAttribute(USER_ID, userByLogin.getId());
        session.setAttribute(clientName, req.getParameter(clientName));
        session.setAttribute(license, req.getParameter(license));

        insertUserIdIntoOrderDB(req, userByLogin.getId());
    }

    private void insertUserIdIntoOrderDB(HttpServletRequest req, long userId) {
        OrderServise orderServise = new OrderServiceImpl();
        orderServise.createOrder(userId);
        Order orderId = orderServise.getOrderByUserId(userId);
        req.getSession().setAttribute(ORDER_ID, orderId.getId());
    }
}
