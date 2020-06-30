package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.dao.ConnectionToDB;
import com.jtmog.alukyanov.dao.impl.OrderGoodDAO;
import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.OrderGood;
import com.jtmog.alukyanov.servise.GoodService;
import com.jtmog.alukyanov.servise.OrderGoodServise;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderGoodServiseImpl implements OrderGoodServise {
    private static final Logger logger = Logger.getLogger(OrderGoodServiseImpl.class);

    private static final String GOOD_ID = "goodId";
    private static final String ORDER_ID = "orderId";
    private static final String BUCKET = "bucket";

    @Override
    public boolean createOrderGood(long orderId, long goodId) {
        try (Connection connection = ConnectionToDB.openConnection()) {
            OrderGoodDAO orderGoodDAO = new OrderGoodDAO(connection);
            OrderGood orderGood = new OrderGood(orderId, goodId);
            orderGoodDAO.insert(orderGood);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<OrderGood> getAllGoodByOrderId(long orderId) {
        List<OrderGood> result = null;
        try (Connection connection = ConnectionToDB.openConnection()) {
            OrderGoodDAO orderGoodDAO = new OrderGoodDAO(connection);
            result = orderGoodDAO.getAllByOrderId(orderId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void createOrderGood(HttpServletRequest req) {
        Long orderId = (Long) req.getSession().getAttribute(ORDER_ID);
        Long goodId = Long.parseLong(req.getParameter(GOOD_ID));

        OrderGoodServise orderGoodServise = new OrderGoodServiseImpl();
        orderGoodServise.createOrderGood(orderId, goodId);
    }

    @Override
    public void readOrderGoodByOrderIdFromDB(HttpServletRequest req) {
        Long orderId = (Long) req.getSession().getAttribute(ORDER_ID);

        OrderGoodServise orderGoodServise = new OrderGoodServiseImpl();
        List<OrderGood> allGoodByOrderId = orderGoodServise.getAllGoodByOrderId(orderId);

        List<Good> listGoodByOrderGood = getListGoodByOrderGood(allGoodByOrderId);
        req.setAttribute(BUCKET, listGoodByOrderGood);
    }

    private List<Good> getListGoodByOrderGood(List<OrderGood> list) {
        List<Long> goodIdList = new ArrayList<>();
        for (OrderGood orderGood : list) {
            long goodId = orderGood.getGoodId();
            goodIdList.add(goodId);
        }

        List<Good> goodList = new ArrayList<>();
        GoodService goodService = new GoodServiceImpl();
        for (Long goodId : goodIdList) {
            Good goodById = goodService.getGoodById(goodId);
            goodList.add(goodById);
        }
        return goodList;
    }
}
