package com.jtmog.alukyanov.task1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class FixedListTest {
    FixedList<String> testString;
    FixedList<Integer> testInteger;

    @Before
    public void setUp() {
        testString = new FixedList<String>();
        testInteger = new FixedList<Integer>();
    }

    @Test
    public void testSizeZero() {
        Assert.assertEquals(0, testInteger.size());
    }

    @Test
    public void testSize() {
        testInteger.add(1);
        testInteger.add(2);
        testInteger.add(3);
        Assert.assertEquals(3, testInteger.size());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertEquals(true, testInteger.isEmpty());
    }

    @Test
    public void testIsEmptyNegative() {
        testInteger.add(1);
        Assert.assertEquals(false, testInteger.isEmpty());
    }

    @Test
    public void testAddInteger() {
        testInteger.add(13);
        Assert.assertEquals(Integer.valueOf(13), testInteger.get(0));
    }

    @Test
    public void testAddString() {
        testString.add("Hello");
        Assert.assertEquals("Hello", testString.get(0));
    }

    @Test(expected = ArrayElementsFullExeption.class)
    public void testAddExceptionFull() {
        testInteger = new FixedList<>(3);
        testInteger.add(1);
        testInteger.add(2);
        testInteger.add(3);
        testInteger.add(4);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddIndexExceptionOutOf() {
        testInteger.add(3, 13);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddIndexExceptionNegative() {
        testInteger.add(-3, 13);
    }

    @Test
    public void testAddIndex() {
        testInteger.add(1);
        testInteger.add(2);
        testInteger.add(3);
        testInteger.add(1, 13);
        Assert.assertEquals(Integer.valueOf(13), testInteger.get(1));
    }

    @Test
    public void testAddAll() {
        testInteger.addAll(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Assert.assertEquals(Integer.valueOf(1), testInteger.get(0));
        Assert.assertEquals(Integer.valueOf(2), testInteger.get(1));
        Assert.assertEquals(Integer.valueOf(3), testInteger.get(2));
    }

    @Test(expected = ArrayElementsFullExeption.class)
    public void testAddAllExceptionFull() {
        testInteger = new FixedList<>(3);
        testInteger.addAll(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void testAddAllIndex() {
        testInteger.add(1);
        testInteger.add(2);
        testInteger.addAll(1, new ArrayList<>(Arrays.asList(3, 4)));
        Assert.assertEquals(Integer.valueOf(1), testInteger.get(0));
        Assert.assertEquals(Integer.valueOf(3), testInteger.get(1));
        Assert.assertEquals(Integer.valueOf(4), testInteger.get(2));
        Assert.assertEquals(Integer.valueOf(2), testInteger.get(3));
    }

    @Test(expected = ArrayElementsFullExeption.class)
    public void testAddAllIndexExceptionFull() {
        testInteger = new FixedList<>(3);
        testInteger.addAll(1, new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
    }

    @Test(expected = ArrayElementsFullExeption.class)
    public void testAddAllIndexException() {
        testInteger = new FixedList<>(3);
        testInteger.addAll(1, new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void testRemoveIndex() {
        testInteger.add(1);
        testInteger.add(2);
        testInteger.add(3);
        testInteger.remove(1);
        Assert.assertEquals(Integer.valueOf(1), testInteger.get(0));
        Assert.assertEquals(Integer.valueOf(3), testInteger.get(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveIndexException() {
        testInteger.remove(1);
    }

    @Test
    public void testGet() {
        testInteger.add(1);
        testInteger.add(2);
        Assert.assertEquals(Integer.valueOf(2), testInteger.get(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetException() {
        testInteger.get(1);
    }

    @Test
    public void testSet() {
        testInteger.add(1);
        testInteger.set(0, 13);
        Assert.assertEquals(Integer.valueOf(13), testInteger.get(0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetException() {
        testInteger.set(5, 13);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testToArray() {
        testInteger.toArray();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testContainsAll() {
        testInteger.containsAll(Arrays.asList(2, 4));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveAll() {
        testInteger.removeAll(Arrays.asList(2, 4));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRetainAll() {
        testInteger.retainAll(Arrays.asList(2, 4));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLastIndexOf() {
        testInteger.lastIndexOf(2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testListIterator() {
        testInteger.listIterator();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testListIteratorIndex() {
        testInteger.listIterator(2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSubList() {
        testInteger.subList(0, 1);
    }
}