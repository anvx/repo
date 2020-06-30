package com.jtmog.alukyanov.model;

import com.jtmog.alukyanov.entity.Good;

import java.util.HashMap;
import java.util.Map;

public class ModelGood {

    private static ModelGood instance = new ModelGood();

    private Map<String, Good> mapOfGoods;

    public static ModelGood getInstance() {
        return instance;
    }

    private ModelGood() {
        mapOfGoods = new HashMap<>();
        setMapOfGoods();
    }

    private void setMapOfGoods() {
        mapOfGoods.put("Book", new Good("Book", 5L));
        mapOfGoods.put("Pencil", new Good("Pencil", 2L));
        mapOfGoods.put("Beer", new Good("Beer", 8L));
        mapOfGoods.put("Notebook", new Good("Notebook", 1L));
    }

    public Map<String, Good> getMapOfGoods() {
        return mapOfGoods;
    }
}
