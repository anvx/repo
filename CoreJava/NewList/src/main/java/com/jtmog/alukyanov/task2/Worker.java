package com.jtmog.alukyanov.task2;

public class Worker {
    private String name;
    private Qualification qualification;

    Worker(String name, Qualification qualification) {
        this.name = name;
        this.qualification = qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public String getName() {
        return name;
    }

    public Qualification getQualification() {
        return qualification;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                '}';
    }
}
