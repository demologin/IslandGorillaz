package com.javarush.island.zubakha.services;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;
import com.javarush.island.zubakha.entity.map.IslandSimulation;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CountDownLatch;


public class AnimalHpDecreaseTask implements Runnable {
    private double healthDecrease = 15;
    private final CountDownLatch latch;
    @Getter
    private int animalsDiedByHungry;

    public AnimalHpDecreaseTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsDiedByHungry = 0;
        List<Animal> animals = GameMap.
                getInstance().
                getAllAnimals().
                stream().
                filter(c -> c.getMaxFood() > 0).toList();
        if (IslandSimulation.getInstance().getTimeNow() / 60 >= 3) {
            healthDecrease = healthDecrease * 2;
        }
        for (Animal animal : animals) {
            double healthToDecrease = animal.getMaxFood() * healthDecrease / 100.0;
            if (animal.getHealth() - healthToDecrease > 0) {
                animal.setHealth(animal.getHealth() - healthToDecrease);
            } else {
                Cell cell = GameMap.getInstance().
                        getLocation(animal.getRow(), animal.getCol());
                GameMap.
                        getInstance().
                        removeAnimal(animal, cell.getRow(), cell.getCol());
                animalsDiedByHungry++;
            }
        }
        latch.countDown();
    }
}
