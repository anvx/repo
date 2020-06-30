package com.jtmog.alukyanov.servise;

import com.jtmog.alukyanov.entity.OrderGood;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderGoodServise {
    boolean createOrderGood(long orderId, long goodId);

    List<OrderGood> getAllGoodByOrderId(long orderId);

    void createOrderGood(HttpServletRequest req);

    void readOrderGoodByOrderIdFromDB(HttpServletRequest req);
}
