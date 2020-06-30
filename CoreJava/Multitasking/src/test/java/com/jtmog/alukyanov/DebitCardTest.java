package com.jtmog.alukyanov;

import com.jtmog.alukyanov.card.DebitCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DebitCardTest {
    private DebitCard debitCard;

    @Before
    public void setUp() {
        debitCard = new DebitCard("John", 250);
    }

    @Test
    public void testAddToDebitCard() {
        debitCard.addToBalance(100);
        Assert.assertEquals(350, debitCard.getBalance());
    }

    @Test
    public void testWithdrawFromDebitCardPositive() {
        debitCard.withdrawFromBalance(100);
        Assert.assertEquals(150, debitCard.getBalance());
    }

    @Test
    public void testWithdrawFromDebitCardNegative() {
        debitCard.withdrawFromBalance(400);
        Assert.assertEquals(250, debitCard.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawFromDebitCardNegativeAmount() {
        debitCard.withdrawFromBalance(-100);
    }
}