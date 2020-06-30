package com.jtmog.alukyanov.entity;

public class Order {

    private long id;
    private long userId;
    private long totalPrice;

    public Order() {
    }

    public Order(long userId) {
        this.userId = userId;
    }

    public Order(long userId, long totalPrice) {
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (userId != order.userId) return false;
        return totalPrice == order.totalPrice;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (totalPrice ^ (totalPrice >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format("Order{id=%d, userId=%d, totalPrice=%d}", id, userId, totalPrice);
    }
}
