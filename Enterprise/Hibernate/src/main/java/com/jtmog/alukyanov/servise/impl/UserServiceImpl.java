package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.dao.impl.UserDAO;
import com.jtmog.alukyanov.entity.User;
import com.jtmog.alukyanov.servise.OrderService;
import com.jtmog.alukyanov.servise.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    @Transactional
    public boolean createUser(String login, String password) {
        throw new UnsupportedOperationException("Not yet.");
    }

    @Override
    @Transactional
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
    @Transactional
    public User getUserByLogin(String login) {
        logger.info(this.getClass().getSimpleName() + " called getUserByLogin method");
        List<User> user = userDAO.read(login);
        if(user.isEmpty()){
            return null;
        }
        return user.get(0);
    }

    private void insertUserIdIntoOrderDB(long userId) {
        logger.info(this.getClass().getSimpleName() + " called insertUserIdIntoOrderDB method");
        orderService.createOrder(new User(userId));
    }
}
