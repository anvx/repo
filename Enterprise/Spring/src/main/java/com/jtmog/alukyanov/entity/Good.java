package com.jtmog.alukyanov.entity;

public class Good {

    private long id;
    private String title;
    private long price;

    public Good() {
    }

    public Good(String name, long price) {
        this.title = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Good good = (Good) o;

        if (price != good.price) return false;
        return title != null ? title.equals(good.title) : good.title == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (int) (price ^ (price >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format("Good{id=%d, title='%s', price=%d}", id, title, price);
    }
}

