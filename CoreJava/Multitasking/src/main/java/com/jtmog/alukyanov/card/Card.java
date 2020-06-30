package com.jtmog.alukyanov.card;

public abstract class Card {
    protected String name;
    protected long balance;

    public Card(String name) {
        this.name = name;
    }

    public Card(String name, long balance) {
        this(name);
        if (balance < 0) {
            this.balance = 0;
            System.out.println("Please check the balance, it should be above 0." +
                    " Now it's set to 0. If you sure that balance should be negative" +
                    " please use <setBalance(long balance)>.");
        } else {
            this.balance = balance;
        }
    }

    public String getName() {
        return name;
    }

    public long getBalance() {
        return balance;
    }

    public boolean addToBalance(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Value can't be negative");
        } else {
            balance += amount;
            return true;
        }
    }

    public abstract boolean withdrawFromBalance(long amount);

    public double converseInto(double conversion) {
        return balance * conversion;
    }
}
