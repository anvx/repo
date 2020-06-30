package com.jtmog.alukyanov.entity;

public class OrderGood {

    private long id;
    private long orderId;
    private long goodId;

    public OrderGood() {
    }

    public OrderGood(long orderId, long goodId) {
        this.orderId = orderId;
        this.goodId = goodId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderGood orderGood = (OrderGood) o;

        if (id != orderGood.id) return false;
        if (orderId != orderGood.orderId) return false;
        return goodId == orderGood.goodId;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (goodId ^ (goodId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format("OrderGood{id=%d, orderId=%d, goodId=%d}", id, orderId, goodId);
    }
}
