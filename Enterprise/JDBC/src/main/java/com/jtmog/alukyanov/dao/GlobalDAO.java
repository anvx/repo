package com.jtmog.alukyanov.dao;

public interface GlobalDAO<K, V> {
    boolean insert(K model);

    K read(V key);

    void update(K model);

    boolean delete(K model);
}
