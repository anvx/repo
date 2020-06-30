package com.jtmog.alukyanov.model;

import com.jtmog.alukyanov.entity.User;

import java.util.HashMap;
import java.util.Map;

public class Model {

    private static Model instance = new Model();

    private Map<String, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();
    }

    public void add(String name, User user) {
        model.put(name, user);
    }

    public Map<String, User> map() {
        return model;
    }
}
