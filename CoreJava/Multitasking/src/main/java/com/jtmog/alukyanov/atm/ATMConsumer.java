package com.jtmog.alukyanov.atm;

import com.jtmog.alukyanov.card.Card;

import java.util.Random;

public class ATMConsumer extends ATM implements Runnable {
    private Card card;

    public ATMConsumer(Card card) {
        this.card = card;
    }

    @Override
    public synchronized void run() {
        Random random = new Random();
        while (ATM.operationAllowed) {

            synchronized (card) {
                System.out.println("\nThread: " + Thread.currentThread().getName()
                        + " is awake. Current balance:" + card.getBalance());
                ATM.operationAllowed = card.withdrawFromBalance((random.nextInt(5) + 6));
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
