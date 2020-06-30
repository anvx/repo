package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.model.ModelGood;
import com.jtmog.alukyanov.servise.GoodServise;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoodServiseImpl implements GoodServise {
    public void getOptions(HttpServletRequest req) {
        Map<String, Good> model = ModelGood.getInstance().getMapOfGoods();
        List<Good> goodFromDB = new ArrayList<>();
        for (Map.Entry<String, Good> map : model.entrySet()) {
            goodFromDB.add(map.getValue());
        }
        req.setAttribute("goodFromDB", goodFromDB);
    }
}
