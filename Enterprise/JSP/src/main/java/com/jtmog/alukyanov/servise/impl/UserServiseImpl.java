package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.entity.User;
import com.jtmog.alukyanov.model.Model;
import com.jtmog.alukyanov.servise.UserServise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserServiseImpl implements UserServise {
    private String license = "license";
    private String clientName = "clientName";

    public void createUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute(clientName) == null ||
                !session.getAttribute(clientName).equals(req.getParameter(clientName))) {
            session.setAttribute(clientName, req.getParameter(clientName));

            Model model = Model.getInstance();
            User user = new User(req.getParameter(clientName));
            if (!model.map().containsValue(user)) {
                model.add(req.getParameter(clientName), user);
            }
        }
        session.setAttribute(license, req.getParameter(license));
    }
}
