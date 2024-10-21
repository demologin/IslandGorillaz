package com.javarush.island.zubakha.services;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.LifeForm;
import com.javarush.island.zubakha.entity.Plant;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class AnimalEatTask implements Runnable {
    private final CountDownLatch latch;
    @Getter
    private int animalsEaten;
    public AnimalEatTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsEaten = 0;
        List<Animal> animals = GameMap.getInstance().getAllAnimals();
        List<LifeForm> lifeFormsEaten = new ArrayList<>();
        Iterator<Animal> iterator = animals.iterator();

            while (iterator.hasNext()) {
                Animal currentAnimal = iterator.next();
                if (currentAnimal.getMaxFood() > 0) {
                    Cell cell = GameMap.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getCol());
                    List<LifeForm> locationLifeForms = cell.getLifeForms();

                    if (!locationLifeForms.isEmpty()) {
                        for (LifeForm lifeForm : locationLifeForms) {
                            if (currentAnimal.getChanceToEat(lifeForm.getName()) > 0 && !(lifeFormsEaten.contains(lifeForm))) {
                                boolean isEaten = currentAnimal.eat(lifeForm);

                                if (isEaten) {
                                    if (lifeForm instanceof Animal animalEaten) {
                                        if (cell.getAnimals().contains(animalEaten)) {
                                            GameMap.getInstance().removeAnimal(animalEaten, cell.getRow(), cell.getCol());
                                        }
                                        lifeFormsEaten.add(animalEaten);
                                        animalsEaten++;
                                    } else {
                                        Plant plant = (Plant) lifeForm;
                                        if (!cell.getPlants().isEmpty()) {
                                            GameMap.getInstance().removePlant(plant, cell.getRow(), cell.getCol());
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                iterator.remove();

        }
        latch.countDown();
    }
}
