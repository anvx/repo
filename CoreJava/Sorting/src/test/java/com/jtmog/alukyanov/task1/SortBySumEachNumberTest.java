package com.jtmog.alukyanov.task1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SortBySumEachNumberTest {
    private SortBySumEachNumber test;
    private List<Integer> testList = new ArrayList<>(Arrays.asList(1, 4, 5, 0, -3, -231));

    @Before
    public void setUp() {
        test = new SortBySumEachNumber();
    }

    @Test
    public void testCompareLess() {
        Assert.assertEquals(-1, test.compare(2, 4));

    }

    @Test
    public void testCompareMore() {
        Assert.assertEquals(1, test.compare(4, 2));

    }
    @Test
    public void testCompareEquals() {
        Assert.assertEquals(0, test.compare(2, 2));

    }

    @Test
    public void testCompareWithZero() {
        Assert.assertEquals(1, test.compare(4, 0));

    }

    @Test (expected = NullPointerException.class)
    public void testCompareWidthZero() {
        Assert.assertEquals(1, test.compare(null, 0));
    }

    @Test
    public void testCompareNegative() {
        Assert.assertEquals(-1, test.compare(-7, -1));
    }

    @Test
    public void testCompareNegativeAndZero() {
        Assert.assertEquals(-1, test.compare(-7, 0));
    }

    @Test
    public void testCompareNegativeAndPositive() {
        Assert.assertEquals(-1, test.compare(-7, 0));
    }
}