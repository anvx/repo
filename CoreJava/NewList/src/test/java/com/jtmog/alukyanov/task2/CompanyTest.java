package com.jtmog.alukyanov.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CompanyTest {
    private Company test;
    Map<Qualification, List<Worker>> testWorkers = new EnumMap<>(Qualification.class);

    @Before
    public void setUp() {
        test = new Company("Company", 200);

        {
            for (Qualification element : Qualification.values()) {
                testWorkers.put(element, new LinkedList<>());
            }
        }
    }

    @Test
    public void testSetName() {
        test.setName("My Company");
        Assert.assertEquals("My Company", test.getName());
    }

    @Test
    public void testSetValue() {
        test.setValue(730);
        Assert.assertEquals(730, test.getValue());
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Company", test.getName());
    }

    @Test
    public void testGetValue() {
        Assert.assertEquals(200, test.getValue());
    }

    @Test
    public void testGetWorkers() {
        Assert.assertEquals(testWorkers, test.getWorkers());
    }


    @Test
    public void testAddWorkers() {
        testWorkers.put(Qualification.ARCHITECT, new ArrayList<Worker>(Arrays.asList(new Worker("Liza", Qualification.ARCHITECT))));
        test.addWorkers(Qualification.ARCHITECT, new Worker("Liza", Qualification.ARCHITECT));

        System.out.println("Test addWorkers(Workers worker):");
        System.out.println("Excpected :" + testWorkers.entrySet());
        System.out.println("Actual    :" + test.getWorkers().entrySet());
    }

    @Test
    public void testAddWorkersAsArray() {
        testWorkers.put(Qualification.ARCHITECT, new ArrayList<Worker>(Arrays.asList(new Worker("Liza", Qualification.ARCHITECT))));
        testWorkers.put(Qualification.BUILDER, new ArrayList<Worker>(Arrays.asList(new Worker("Oleg", Qualification.BUILDER))));

        Worker worker1 = new Worker("Liza", Qualification.ARCHITECT);
        Worker worker2 = new Worker("Oleg", Qualification.BUILDER);
        Worker[] workers = new Worker[] {worker1, worker2};

        test.addWorkers(workers);
        System.out.println("Test addWorkers(Workers[] array):");
        System.out.println("Excpected :" + testWorkers.entrySet());
        System.out.println("Actual    :" + test.getWorkers().entrySet());
    }
}