package com.jtmog.alukyanov.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetOperationsTest {
    private Set<Integer> set1;
    private Set<Integer> set2;

    @Before
    public void setUp() {
        set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        set2 = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
    }

    @Test
    public void testUnion() {
        Integer[] exp = {1, 2, 3, 4, 5, 6, 7};
        Assert.assertArrayEquals(exp, SetOperations.union(set1, set2).<Integer>toArray());

    }

    @Test
    public void testIntersection() {
        Integer[] exp = {3, 4, 5};
        Assert.assertArrayEquals(exp, SetOperations.intersection(set1, set2).<Integer>toArray());
    }

    @Test
    public void testMinus() {
        Integer[] exp = {1, 2};
        Assert.assertArrayEquals(exp, SetOperations.minus(set1, set2).<Integer>toArray());
    }

    @Test
    public void testComplement() {
        Integer[] exp = {1, 2, 6, 7};
        Assert.assertArrayEquals(SetOperations.complement(set1, set2).<Integer>toArray(), exp);
    }

    @Test(expected = NullPointerException.class)
    public void testNull() {
        Integer[] exp = {};
        Assert.assertArrayEquals(SetOperations.complement(null, null).<Integer>toArray(), exp);
    }

    @Test
    public void testZeroArray() {
        Integer[] exp = {};
        set1 = new HashSet<>(Arrays.asList());
        set2 = new HashSet<>(Arrays.asList());
        Assert.assertArrayEquals(SetOperations.complement(set1, set2).<Integer>toArray(), exp);
    }

}