package com.jtmog.alukyanov.servise.impl;

import com.jtmog.alukyanov.dao.impl.GoodDAO;
import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.servise.GoodService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {
    private static final Logger logger = Logger.getLogger(GoodServiceImpl.class);

    private final GoodDAO goodDAO;

    public GoodServiceImpl(GoodDAO goodDAO) {
        this.goodDAO = goodDAO;
    }

    @Override
    @Transactional
    public Good getGoodByTitle(String title) {
        logger.info(this.getClass().getSimpleName() + " called getGoodByTitle method");
        return goodDAO.read(title).get(0);
    }

    @Override
    @Transactional
    public Good getGoodById(long goodId) {
        logger.info(this.getClass().getSimpleName() + " called getGoodById method");
        return goodDAO.read(goodId);
    }

    @Override
    @Transactional
    public List<Good> getAllGood() {
        logger.info(this.getClass().getSimpleName() + " called getAllGood method");
        return goodDAO.getAll();
    }
}
