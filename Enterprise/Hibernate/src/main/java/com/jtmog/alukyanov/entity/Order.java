package com.jtmog.alukyanov.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Bucket")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User user;
    private long totalPrice;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Good> goods;

    public Order() {
    }

    public Order(User user) {
        this.user = user;
    }

    public Order(User user, long totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (totalPrice != order.totalPrice) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        return goods != null ? goods.equals(order.goods) : order.goods == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (int) (totalPrice ^ (totalPrice >>> 32));
        result = 31 * result + (goods != null ? goods.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Order{").append("id=").append(id).append(", user=").append(user).append(", totalPrice=").append(totalPrice).append(", goods=").append(goods).append('}').toString();
    }
}
