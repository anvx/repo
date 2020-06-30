package com.jtmog.alukyanov.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTest {
    Worker test;

    @Before
    public void setUp() {
        test = new Worker("Oleg", Qualification.ARCHITECT);
    }

    @Test
    public void testSetQualification() {
        test.setQualification(Qualification.BUILDER);
        Assert.assertEquals(Qualification.BUILDER, test.getQualification());

    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Oleg", test.getName());
    }

    @Test
    public void testGetQualification() {
        Assert.assertEquals(Qualification.ARCHITECT, test.getQualification());
    }
}