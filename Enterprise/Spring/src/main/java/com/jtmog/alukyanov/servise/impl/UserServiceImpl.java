package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.dao.impl.UserDAO;
import com.jtmog.alukyanov.entity.User;
import com.jtmog.alukyanov.servise.OrderService;
import com.jtmog.alukyanov.servise.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;

    private final OrderService orderService;

    public UserServiceImpl(UserDAO userDAO, OrderService orderService) {
        this.userDAO = userDAO;
        this.orderService = orderService;
    }

    @Override
    public boolean createUser(String login, String password) {
        throw new UnsupportedOperationException("Not yet.");
    }

    @Override
    public void createUser(String clientName) {
        logger.info(this.getClass().getSimpleName() + " called createUser method");
        User userByLogin = getUserByLogin(clientName);
        if (userByLogin == null) {
            User user = new User(clientName);
            userDAO.insert(user);
            userByLogin = getUserByLogin(clientName);
            insertUserIdIntoOrderDB(userByLogin.getId());
        }
    }

    @Override
    public User getUserByLogin(String login) {
        logger.info(this.getClass().getSimpleName() + " called getUserByLogin method");
        return userDAO.read(login).orElse(null);
    }

    private void insertUserIdIntoOrderDB(long userId) {
        logger.info(this.getClass().getSimpleName() + " called insertUserIdIntoOrderDB method");
        orderService.createOrder(userId);
    }
}
