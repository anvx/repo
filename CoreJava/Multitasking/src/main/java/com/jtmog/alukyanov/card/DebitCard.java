package com.jtmog.alukyanov.card;

public class DebitCard extends Card {
    public DebitCard(String name) {
        super(name);
    }

    public DebitCard(String name, long balance) {
        super(name, balance);
    }

    public synchronized boolean withdrawFromBalance(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Value can't be negative");
        }
        if (balance > amount) {
            balance -= amount;
            return true;
        } else {
            System.out.println("You don't have " + amount + " USD on your account.");
            return false;
        }
    }

    @Override
    public synchronized boolean addToBalance(long amount) {
        return super.addToBalance(amount);
    }
}
