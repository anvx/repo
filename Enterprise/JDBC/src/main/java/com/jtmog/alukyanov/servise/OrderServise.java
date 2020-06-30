package com.jtmog.alukyanov.servise;

import com.jtmog.alukyanov.entity.Order;

import javax.servlet.http.HttpServletRequest;

public interface OrderServise {
    boolean createOrder(long userId);

    Order getOrderByUserId(long userId);

    boolean updateOrder(long userId, long totalPrice);

    void updateTotalPriceInOrder(HttpServletRequest req);

    void readTotalPriceFromOrderDB(HttpServletRequest req);
}
