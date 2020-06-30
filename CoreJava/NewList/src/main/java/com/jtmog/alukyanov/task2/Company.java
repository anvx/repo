package com.jtmog.alukyanov.task2;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Company {
    private String name;
    private int value;
    private Map<Qualification, List<Worker>> workers = new EnumMap<>(Qualification.class);

    Company(String name, int value) {
        this.name = name;
        this.value = value;
        for (Qualification element : Qualification.values()) {
            workers.put(element, new LinkedList<>());
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addWorkers(Qualification qualification, Worker worker) {
        workers.get(qualification).add(worker);
    }

    public void addWorkers(Worker[] workers) {
        for (Worker worker : workers) {
            addWorkers(worker.getQualification(), worker);
        }
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Map<Qualification, List<Worker>> getWorkers() {
        return workers;
    }
}
