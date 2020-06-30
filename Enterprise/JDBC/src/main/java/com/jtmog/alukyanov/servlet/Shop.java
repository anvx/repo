package com.jtmog.alukyanov.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Shop extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Shop.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("views/shop.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
