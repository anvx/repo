package com.jtmog.alukyanov.task2;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Tender {
    private List<Company> listOfCompanies = new ArrayList<>();

    private Map<Qualification, Integer> listOfRequirements = new EnumMap<>(Qualification.class);

    {
        listOfRequirements.put(Qualification.ARCHITECT, 1);
        listOfRequirements.put(Qualification.BUILDER, 1);
        listOfRequirements.put(Qualification.ELECTRICIAN, 1);
    }

    public void createCompany() {
        Company companyOne = new Company("Rogs", 350);

        Worker[] workersOne = new Worker[]{new Worker("Jonny", Qualification.BUILDER),
                new Worker("Greg", Qualification.DESIGNER),
                new Worker("Dima", Qualification.PLUMBER),
                new Worker("Vlad", Qualification.ELECTRICIAN)
        };
        companyOne.addWorkers(workersOne);
        listOfCompanies.add(companyOne);

        Company companyTwo = new Company("Greg and Sons", 450);

        Worker[] workersTwo = new Worker[]{new Worker("Molly", Qualification.ELECTRICIAN),
                new Worker("Misha", Qualification.PLUMBER),
                new Worker("Ivan", Qualification.BUILDER),
                new Worker("Greg", Qualification.ARCHITECT)
        };
        companyTwo.addWorkers(workersTwo);
        listOfCompanies.add(companyTwo);

        Company companyThree = new Company("Hoko", 700);

        Worker[] workersThree = new Worker[]{new Worker("Lina", Qualification.ELECTRICIAN),
                new Worker("Aleks", Qualification.PLUMBER),
                new Worker("Vasya", Qualification.BUILDER),
                new Worker("Barak", Qualification.ARCHITECT),
                new Worker("Donald", Qualification.ARCHITECT)
        };
        companyThree.addWorkers(workersThree);
        listOfCompanies.add(companyThree);
    }

    public List<Company> chooseCompany() {
        List<Company> listOfWinners = new ArrayList<>(listOfCompanies.size());
        for (Company listOfCompany : listOfCompanies) {
            if (countRequirements(listOfCompany) == listOfRequirements.size()) {
                listOfWinners.add(listOfCompany);
            }

        }
        return listOfWinners;
    }

    private int countRequirements(Company listOfCompany) {
        int countOfNumber = 0;
        for (Map.Entry<Qualification, List<Worker>> mapCompany : listOfCompany.getWorkers().entrySet()) {
            for (Map.Entry<Qualification, Integer> mapRequirement : listOfRequirements.entrySet()) {
                if (mapCompany.getKey() == mapRequirement.getKey()
                        && mapRequirement.getValue() <= mapCompany.getValue().size()) {
                    countOfNumber++;
                }
            }
        }
        return countOfNumber;
    }

    public String selectWinner(List<Company> listOfWinners) {
        int min = -1;
        String winner = "Nobody";
        for (Company list : listOfWinners) {
            if (min == -1) {
                min = list.getValue();
                winner = "Winner is: " + list.getName() + " with price " + list.getValue();
            }
            if (list.getValue() < min) {
                min = list.getValue();
                winner = "Winner is: " + list.getName() + " with price " + list.getValue();
            }

        }
        return winner;
    }

    public void print(String string) {
        System.out.println(string);
    }
}
