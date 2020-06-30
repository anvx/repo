package com.jtmog.alukyanov.servlet;

import com.jtmog.alukyanov.entity.User;
import com.jtmog.alukyanov.model.Model;
import com.jtmog.alukyanov.servise.GoodServise;
import com.jtmog.alukyanov.servise.OrderServise;
import com.jtmog.alukyanov.servise.UserServise;
import com.jtmog.alukyanov.servise.impl.GoodServiseImpl;
import com.jtmog.alukyanov.servise.impl.OrderServiseImpl;
import com.jtmog.alukyanov.servise.impl.UserServiseImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderPage extends HttpServlet {
    private UserServise userServise = new UserServiseImpl();
    private GoodServise goodServise = new GoodServiseImpl();
    private OrderServise orderServise = new OrderServiseImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        userServise.createUser(req);
        orderServise.printCurrentOrder(req);
        goodServise.getOptions(req);
        try {
            req.getRequestDispatcher("views/order.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User currentUser = Model.getInstance().map().get(req.getParameter("clientName"));

        currentUser.addOrder(req.getParameter("orderGood"));

        doGet(req, resp);
    }
}
