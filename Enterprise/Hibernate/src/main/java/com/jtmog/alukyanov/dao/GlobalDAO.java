package com.jtmog.alukyanov.dao;

import java.util.List;
import java.util.Optional;

public interface GlobalDAO<K, V> {
    void insert(K model);

    List<K> read(V key);

    void update(K model);

    void delete(K model);
}
