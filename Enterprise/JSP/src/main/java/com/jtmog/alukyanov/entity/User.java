package com.jtmog.alukyanov.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private List<String> orderList;

    public User(String name) {
        this.name = name;
        orderList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addOrder(String name) {
        orderList.add(name);
    }

    public List<String> getOrderList() {
        return orderList;
    }

    @Override
    public String toString() {
        return String.format("User{name='%s', orderList=%s}", name, orderList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return orderList != null ? orderList.equals(user.orderList) : user.orderList == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (orderList != null ? orderList.hashCode() : 0);
        return result;
    }
}
