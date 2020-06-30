package com.jtmog.alukyanov.servlet;

import com.jtmog.alukyanov.servise.OrderGoodServise;
import com.jtmog.alukyanov.servise.OrderServise;
import com.jtmog.alukyanov.servise.impl.OrderGoodServiseImpl;
import com.jtmog.alukyanov.servise.impl.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Checkout extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Checkout.class);

    private static OrderServise orderServise;
    private static OrderGoodServise orderGoodServise;

    @Override
    public void init() {
        Checkout.orderServise = new OrderServiceImpl();
        Checkout.orderGoodServise = new OrderGoodServiseImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Checkout.orderGoodServise.readOrderGoodByOrderIdFromDB(req);
        Checkout.orderServise.readTotalPriceFromOrderDB(req);
        try {
            req.getRequestDispatcher("views/checkout.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
