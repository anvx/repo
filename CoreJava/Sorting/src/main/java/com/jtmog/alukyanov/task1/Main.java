package com.jtmog.alukyanov.task1;

import java.util.*;

public class Main {
    private static int[] arrayToSort = new int[]{345345,-1,-7,0,84,-634};

    public static void main(String[] args) {
        List <Integer> sortedArray = new ArrayList<>(arrayToSort.length);
        for(Integer value : arrayToSort) {
            sortedArray.add(value);
        }
        sortedArray.sort(new SortBySumEachNumber());
        System.out.println(sortedArray);
    }
}
