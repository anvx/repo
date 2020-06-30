package com.jtmog.alukyanov.servise;

import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.OrderGood;

import java.util.List;

public interface OrderGoodService {
    boolean createOrderGood(long orderId, long goodId);

    List<OrderGood> getAllGoodByOrderId(long orderId);

    List<Good> readOrderGoodByOrderIdFromDB(long id);
}
