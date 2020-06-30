package com.jtmog.alukyanov.servise;

import com.jtmog.alukyanov.entity.Good;

import java.util.List;

public interface GoodService {
    Good getGoodByTitle(String title);

    Good getGoodById(long goodId);

    List<Good> getAllGood();
}
