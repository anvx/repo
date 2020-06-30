package com.jtmog.alukyanov.servlet;

import com.jtmog.alukyanov.servise.GoodService;
import com.jtmog.alukyanov.servise.OrderGoodServise;
import com.jtmog.alukyanov.servise.OrderServise;
import com.jtmog.alukyanov.servise.UserServise;
import com.jtmog.alukyanov.servise.impl.GoodServiceImpl;
import com.jtmog.alukyanov.servise.impl.OrderGoodServiseImpl;
import com.jtmog.alukyanov.servise.impl.OrderServiceImpl;
import com.jtmog.alukyanov.servise.impl.UserServiseImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderPage extends HttpServlet {
    private static final Logger logger = Logger.getLogger(OrderPage.class);

    private static final String CLIENT_NAME = "clientName";
    private static final String LICENSE = "license";

    private static GoodService goodService;
    private static OrderServise orderServise;
    private static OrderGoodServise orderGoodServise;
    private static UserServise userServise;

    @Override
    public void init() throws ServletException {
        OrderPage.goodService = new GoodServiceImpl();
        OrderPage.orderServise = new OrderServiceImpl();
        OrderPage.orderGoodServise = new OrderGoodServiseImpl();
        OrderPage.userServise = new UserServiseImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        OrderPage.userServise.createUser(req, CLIENT_NAME, LICENSE);
        OrderPage.goodService = goodService.createListOfGoodsFromDB();
        req.setAttribute("goodsFromDataBase", goodService.getAllGood());
        OrderPage.orderGoodServise.readOrderGoodByOrderIdFromDB(req);
        OrderPage.orderServise.updateTotalPriceInOrder(req);
        try {
            req.getRequestDispatcher("views/order.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        OrderPage.orderGoodServise.createOrderGood(req);

        doGet(req, resp);
    }
}
