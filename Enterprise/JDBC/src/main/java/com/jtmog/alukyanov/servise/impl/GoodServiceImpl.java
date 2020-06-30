package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.dao.ConnectionToDB;
import com.jtmog.alukyanov.dao.impl.GoodDAO;
import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.servise.GoodService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GoodServiceImpl implements GoodService {
    private static final Logger logger = Logger.getLogger(GoodServiceImpl.class);

    @Override
    public Good getGoodByTitle(String title) {
        Good good = null;
        try (Connection connection = ConnectionToDB.openConnection()) {
            GoodDAO goodDAO = new GoodDAO(connection);
            good = goodDAO.read(title);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return good;
    }

    @Override
    public Good getGoodById(long goodId) {
        Good good = null;
        try (Connection connection = ConnectionToDB.openConnection()) {
            GoodDAO goodDAO = new GoodDAO(connection);
            good = goodDAO.read(goodId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return good;
    }

    @Override
    public List<Good> getAllGood() {
        List<Good> result = null;
        try (Connection connection = ConnectionToDB.openConnection()) {
            GoodDAO goodDAO = new GoodDAO(connection);
            result = goodDAO.getAll();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public GoodService createListOfGoodsFromDB() {
        return new GoodServiceImpl();
    }
}
