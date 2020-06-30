package com.jtmog.alukyanov.servise;

import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.Order;
import com.jtmog.alukyanov.entity.User;

import java.util.List;

public interface OrderService {
    void createOrder(User user);

    Order getOrderByUserId(long userId);

    void updateOrder(long userId, long totalPrice);

    void updateTotalPriceInOrder(List<Good> list, long userId);

    long readTotalPriceFromOrderDB(long userId);

    List<Good> getGoodByUserId(long userId);

    void addGoodToOrder(long orderId, long goodId);
}


