package com.jtmog.alukyanov.atm;

import com.jtmog.alukyanov.card.Card;

public class ATM {
    public static boolean operationAllowed = true;

    public boolean add(Card card, long amount) {
        return card.addToBalance(amount);
    }

    public boolean withdraw(Card card, long amount) {
        return card.withdrawFromBalance(amount);
    }
}
