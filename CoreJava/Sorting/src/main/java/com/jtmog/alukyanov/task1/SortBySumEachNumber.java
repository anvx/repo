package com.jtmog.alukyanov.task1;

import java.util.Comparator;

public class SortBySumEachNumber implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        Integer sum1 = 0;
        Integer sum2 = 0;

        Integer initialValue1 =  o1;
        Integer initialValue2 =  o2;


        while (initialValue1 != 0) {
            sum1 += initialValue1 % 10;
            initialValue1 /= 10;
        }

        while (initialValue2 != 0) {
            sum2 += initialValue2 % 10;
            initialValue2 /= 10;
        }
        return sum1.compareTo(sum2);
    }
}
