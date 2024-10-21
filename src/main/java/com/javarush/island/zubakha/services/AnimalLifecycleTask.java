package com.javarush.island.zubakha.services;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalLifecycleTask implements Runnable {
    @Getter
    private final AnimalEatTask animalEatTask;
    private final AnimalMultiplyTask animalMultiplyTask;
    @Getter
    private final AnimalHpDecreaseTask animalHpDecreaseTask;
    private final CountDownLatch latch;

    public AnimalLifecycleTask() {
        latch = new CountDownLatch(3);
        animalEatTask = new AnimalEatTask(latch);
        animalMultiplyTask = new AnimalMultiplyTask(latch);
        animalHpDecreaseTask = new AnimalHpDecreaseTask(latch);
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(animalEatTask);
        executorService.submit(animalMultiplyTask);
        executorService.submit(animalHpDecreaseTask);
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.submit(new AnimalMoveTask());
    }

    public AnimalMultiplyTask getObjectMultiplyTask() {
        return animalMultiplyTask;
    }

}
