package com.jtmog.alukyanov.atm;

import com.jtmog.alukyanov.card.Card;

import java.util.Random;

public class ATMProducer extends ATM implements Runnable {
    private Card card;

    public ATMProducer(Card card) {
        this.card = card;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (card.getBalance() > 0 && card.getBalance() < 1000 && ATM.operationAllowed) {

            synchronized (card) {
                System.out.println("\nThread: " + Thread.currentThread().getName()
                        + " is awake. Current balance:" + card.getBalance());
                card.addToBalance((random.nextInt(5) + 6));
                if (card.getBalance() >= 1000) {
                    ATM.operationAllowed = false;
                }
                System.out.println("Balance after operation: " + card.getBalance());
            }

            try {
                Thread.sleep(random.nextInt(3000) + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
        System.out.println("Thread out: " + Thread.currentThread().getName());
    }
}
