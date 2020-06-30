package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.dao.impl.OrderGoodDAO;
import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.OrderGood;
import com.jtmog.alukyanov.servise.GoodService;
import com.jtmog.alukyanov.servise.OrderGoodService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderGoodServiceImpl implements OrderGoodService {
    private static final Logger logger = Logger.getLogger(OrderGoodServiceImpl.class);

    private final OrderGoodDAO orderGoodDAO;

    private final GoodService goodService;

    public OrderGoodServiceImpl(OrderGoodDAO orderGoodDAO, GoodService goodService) {
        this.orderGoodDAO = orderGoodDAO;
        this.goodService = goodService;
    }

    @Override
    public boolean createOrderGood(long orderId, long goodId) {
        logger.info(this.getClass().getSimpleName() + " called createOrderGood method");
        OrderGood orderGood = new OrderGood(orderId, goodId);
        return orderGoodDAO.insert(orderGood);
    }

    @Override
    public List<OrderGood> getAllGoodByOrderId(long orderId) {
        logger.info(this.getClass().getSimpleName() + " called getAllGoodByOrderId method");
        return orderGoodDAO.getAllByOrderId(orderId);
    }

    @Override
    public List<Good> readOrderGoodByOrderIdFromDB(long orderId) {
        logger.info(this.getClass().getSimpleName() + " called readOrderGoodByOrderIdFromDB method");
        return getListGoodByOrderGood(getAllGoodByOrderId(orderId));
    }

    private List<Good> getListGoodByOrderGood(List<OrderGood> list) {
        List<Long> goodIdList = new ArrayList<>();
        for (OrderGood orderGood : list) {
            long goodId = orderGood.getGoodId();
            goodIdList.add(goodId);
        }

        List<Good> goodList = new ArrayList<>();
        for (Long goodId : goodIdList) {
            Good goodById = goodService.getGoodById(goodId);
            goodList.add(goodById);
        }
        return goodList;
    }
}
