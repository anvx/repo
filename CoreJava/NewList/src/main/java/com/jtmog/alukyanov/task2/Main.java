package com.jtmog.alukyanov.task2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tender tender = new Tender();
        tender.createCompany();
        List<Company> listOfCompany = tender.chooseCompany();
        String result = tender.selectWinner(listOfCompany);
        tender.print(result);
    }
}
