package com.jtmog.alukyanov;

import com.jtmog.alukyanov.atm.ATM;
import com.jtmog.alukyanov.card.DebitCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ATMTest {
    private ATM atm;
    private DebitCard debitCard;

    @Before
    public void setUp() {
        atm = new ATM();
        debitCard = new DebitCard("John", 250);
    }

    @Test
    public void testAddToDebitCard() {
        atm.add(debitCard, 100);
        Assert.assertEquals(350, debitCard.getBalance());
    }

    @Test
    public void testWithdrawFromDebitCardPositive() {
        atm.withdraw(debitCard, 100);
        Assert.assertEquals(150, debitCard.getBalance());
    }

    @Test
    public void testWithdrawFromDebitCardNegative() {
        atm.withdraw(debitCard, 400);
        Assert.assertEquals(250, debitCard.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawFromDebitCardNegativeAmount() {
        atm.withdraw(debitCard, -100);
    }
}
