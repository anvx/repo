package com.jtmog.alukyanov.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TenderTest {
    Tender test;
    Company companyOne;
    List<Company> listOfCompanies = new ArrayList<>();
    Map<Qualification, Integer> listOfRequirements = new EnumMap<>(Qualification.class);

    @Before
    public void setUp() {
        test = new Tender();
    }

    @Test
    public void testChooseCompany() {


    }

    @Test
    public void testSelectWinner() {
        test.createCompany();
        List<Company> listOfCompany= test.chooseCompany();
        Assert.assertEquals("Winner is: Greg and Sons with price 450", test.selectWinner(listOfCompany));
    }
}