package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.dao.impl.GoodDAO;
import com.jtmog.alukyanov.dao.impl.OrderDAO;
import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.Order;
import com.jtmog.alukyanov.entity.User;
import com.jtmog.alukyanov.servise.OrderService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    private final OrderDAO orderDAO;
    private final GoodDAO goodDAO;

    public OrderServiceImpl(OrderDAO orderDAO, GoodDAO goodDAO) {
        this.orderDAO = orderDAO;
        this.goodDAO = goodDAO;
    }

    @Override
    @Transactional
    public void createOrder(User user) {
        orderDAO.insert(new Order(user));
    }

    @Override
    @Transactional
    public Order getOrderByUserId(long userId) {
        logger.info(this.getClass().getSimpleName() + " called getOrderByUserId method");
        List<Order> read = orderDAO.readByUserId(userId);
        if(read.isEmpty()) {
            return null;
        }
        return read.get(0);
    }

    @Override
    @Transactional
    public void updateOrder(long userId, long totalPrice) {
        logger.info(this.getClass().getSimpleName() + " called updateOrder method");
        Order order = new Order();
        order.setUser(new User(userId));
        order.setTotalPrice(totalPrice);
        orderDAO.update(order);
    }

    @Override
    @Transactional
    public void updateTotalPriceInOrder(List<Good> listGoodByOrderGood, long userId) {
        logger.info(this.getClass().getSimpleName() + " called updateTotalPriceInOrder method");
        long totalPrice = 0;
        for (Good good : listGoodByOrderGood) {
            totalPrice += good.getPrice();
        }
        updateOrder(userId, totalPrice);
    }

    @Override
    @Transactional
    public long readTotalPriceFromOrderDB(long userId) {
        logger.info(this.getClass().getSimpleName() + " called readTotalPriceFromOrderDB method");
        return getOrderByUserId(userId).getTotalPrice();
    }

    @Override
    @Transactional
    public List<Good> getGoodByUserId(long userId) {
        List<Order> orderList = orderDAO.readByUserId(userId);
        Order order = orderList.get(0);
        return order.getGoods();
    }

    @Override
    @Transactional
    public void addGoodToOrder(long orderId, long goodId) {
        Order order = orderDAO.read(orderId).get(0);
        Good good = goodDAO.read(goodId);
        long price = order.getTotalPrice();
        order.getGoods().add(good);
        order.setTotalPrice(price + good.getPrice());
    }
}
