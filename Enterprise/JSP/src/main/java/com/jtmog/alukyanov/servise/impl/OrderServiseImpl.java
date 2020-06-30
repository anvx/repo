package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.User;
import com.jtmog.alukyanov.model.Model;
import com.jtmog.alukyanov.model.ModelGood;
import com.jtmog.alukyanov.servise.OrderServise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiseImpl implements OrderServise {
    private String clientName = "clientName";

    public void printCurrentOrder(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute(clientName);

        User currentUser = Model.getInstance().map().get(user);
        List<String> orderList = currentUser.getOrderList();

        Map<String, Good> mapOfGoods = ModelGood.getInstance().getMapOfGoods();

        List<Good> listOfGoods = new ArrayList<>();
        long valueToPay = 0;

        for (String key : orderList) {
            Good currentGood = mapOfGoods.get(key);
            listOfGoods.add(currentGood);
            valueToPay += currentGood.getPrice();
        }
        req.getSession().getServletContext().setAttribute("listOfGoods", listOfGoods);
        req.getSession().getServletContext().setAttribute("amount", valueToPay);
    }
}
