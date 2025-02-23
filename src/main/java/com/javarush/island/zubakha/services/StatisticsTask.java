package com.javarush.island.zubakha.services;

import com.javarush.island.zubakha.entity.map.GameMap;
import com.javarush.island.zubakha.entity.map.IslandSimulation;
import lombok.Getter;

public class StatisticsTask implements Runnable {
    private boolean isTimeOver;
    private int offspring;
    private int animalsEaten;
    private int animalsDiedByHungry;
    private int countAnimalsEnd;
    private int countPlants;
    @Getter
    private static int currentDay = 0;
    private final AnimalMultiplyTask animalMultiplyTask;
    private final AnimalEatTask animalEatTask;
    private final AnimalHpDecreaseTask animalHpDecreaseTask;

    public StatisticsTask(AnimalEatTask animalEatTask,
                          AnimalHpDecreaseTask animalHpDecreaseTask,
                          AnimalMultiplyTask animalMultiplyTask) {
        this.animalEatTask = animalEatTask;
        this.animalHpDecreaseTask = animalHpDecreaseTask;
        this.animalMultiplyTask = animalMultiplyTask;
    }

    @Override
    public void run() {
        long timeNow = IslandSimulation.
                getInstance().
                getTimeNow();
        isTimeOver = checkTime(timeNow);

        offspring = animalMultiplyTask.getOffspring();
        countAnimalsEnd = GameMap.
                getInstance().
                getAllAnimals().
                size();
        animalsEaten = animalEatTask.
                getAnimalsEaten();
        animalsDiedByHungry = animalHpDecreaseTask.
                getAnimalsDiedByHungry();
        countPlants = GameMap.
                getInstance().
                getAllPlants().
                size();
        printStats();

        if (isTimeOver) {
            IslandSimulation.
                    getInstance().
                    getExecutorService().
                    shutdown();
            System.exit(0);
        }
    }

    private boolean checkTime(long timeNow) {
        return timeNow / 60 >= 5;
    }

    private void printStats() {
        if (isTimeOver) {
            System.out.println("Simulation is over");

        } else {
            System.out.printf("Statistics island day %d.", currentDay);
            currentDay++;
            System.out.println();
        }
        System.out.print("Animals at island: ");
        System.out.println(countAnimalsEnd);

        System.out.print("Animals is die by hungry: ");
        System.out.println(animalsDiedByHungry);

        System.out.print("Animals by eaten: ");
        System.out.println(animalsEaten);

        System.out.print("Offspring were born: ");
        System.out.println(offspring);

        System.out.print("Plants in island: ");
        System.out.println(countPlants);

        System.out.println();
    }

}
