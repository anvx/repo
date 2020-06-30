package com.jtmog.alukyanov.servise;

import com.jtmog.alukyanov.entity.User;

public interface UserService {
    void createUser(String login);

    boolean createUser(String login, String password);

    User getUserByLogin(String login);
}
