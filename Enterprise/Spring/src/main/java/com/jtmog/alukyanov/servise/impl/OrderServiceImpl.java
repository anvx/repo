package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.dao.impl.OrderDAO;
import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.Order;
import com.jtmog.alukyanov.servise.OrderService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void createOrder(long userId) {
        orderDAO.insert(new Order(userId));
    }

    @Override
    public Order getOrderByUserId(long userId) {
        logger.info(this.getClass().getSimpleName() + " called getOrderByUserId method");
        return orderDAO.read(userId).orElse(null);
    }

    @Override
    public void updateOrder(long userId, long totalPrice) {
        logger.info(this.getClass().getSimpleName() + " called updateOrder method");
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        orderDAO.update(order);
    }

    @Override
    public void updateTotalPriceInOrder(List<Good> listGoodByOrderGood, long userId) {
        logger.info(this.getClass().getSimpleName() + " called updateTotalPriceInOrder method");
        long totalPrice = 0;
        for (Good good : listGoodByOrderGood) {
            totalPrice += good.getPrice();
        }
        updateOrder(userId, totalPrice);
    }

    @Override
    public long readTotalPriceFromOrderDB(long userId) {
        logger.info(this.getClass().getSimpleName() + " called readTotalPriceFromOrderDB method");
        return getOrderByUserId(userId).getTotalPrice();
    }
}
