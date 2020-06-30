package com.jtmog.alukyanov;

import com.jtmog.alukyanov.atm.ATMConsumer;
import com.jtmog.alukyanov.atm.ATMProducer;
import com.jtmog.alukyanov.card.Card;
import com.jtmog.alukyanov.card.DebitCard;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static Card card = new DebitCard("Card", 500);

    public static void main(String[] args) {
        runWithExecutors(card);
    }

    private static void runWithExecutors(Card card) {

        Runnable consumer1 = new ATMConsumer(card);
        Runnable consumer2 = new ATMConsumer(card);
        Runnable consumer3 = new ATMConsumer(card);
        Runnable consumer4 = new ATMConsumer(card);

        Runnable producer1 = new ATMProducer(card);
        Runnable producer2 = new ATMProducer(card);
        Runnable producer3 = new ATMProducer(card);

        ExecutorService executorService = Executors.newFixedThreadPool(7);
        executorService.execute(consumer1);
        executorService.execute(consumer2);
        executorService.execute(consumer3);
        executorService.execute(consumer4);

        executorService.execute(producer1);
        executorService.execute(producer2);
        executorService.execute(producer3);

        executorService.shutdown();
    }
}
