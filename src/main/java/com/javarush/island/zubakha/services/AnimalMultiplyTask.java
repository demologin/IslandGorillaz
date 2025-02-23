package com.javarush.island.zubakha.services;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class AnimalMultiplyTask implements Runnable {
    @Getter
    private int offspring;
    private final CountDownLatch latch;

    public AnimalMultiplyTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        offspring = 0;
        List<Animal> animals = GameMap.getInstance().getAllAnimals();
        List<Animal> animalsMultiplied = new ArrayList<>();
        if (!animals.isEmpty()) {
            for (Animal currentAnimal : animals) {
                if (!animalsMultiplied.contains(currentAnimal)) {
                    Cell cell = GameMap.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getCol());
                    List<Animal> locationAnimals = cell.getAnimals();

                    if (locationAnimals.size() > 1) {
                        locationAnimals = locationAnimals.
                                stream().
                                filter(c -> c.getName().
                                        equals(currentAnimal.getName()) && c != currentAnimal).toList();

                        if (!locationAnimals.isEmpty()) {
                            Animal partner = locationAnimals.getFirst();

                            if (!animalsMultiplied.contains(partner)) {
                                currentAnimal.multiply(partner);

                                animalsMultiplied.add(currentAnimal);
                                animalsMultiplied.add(partner);

                                offspring++;
                            }
                        }
                    }
                }
            }
        }
        latch.countDown();
    }

}
