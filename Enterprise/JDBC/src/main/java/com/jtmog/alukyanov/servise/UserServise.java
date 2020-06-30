package com.jtmog.alukyanov.servise;

import com.jtmog.alukyanov.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserServise {
    boolean createUser(String login);

    boolean createUser(String login, String password);

    User getUserByLogin(String login);

    void createUser(HttpServletRequest req, String clientName, String license);
}
