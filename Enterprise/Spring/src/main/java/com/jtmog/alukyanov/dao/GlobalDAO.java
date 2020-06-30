package com.jtmog.alukyanov.dao;

import java.util.Optional;

public interface GlobalDAO<K, V> {
    boolean insert(K model);

    Optional<K> read(V key);

    void update(K model);

    boolean delete(K model);
}
