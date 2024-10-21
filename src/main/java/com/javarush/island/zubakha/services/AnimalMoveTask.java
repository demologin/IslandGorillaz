package com.javarush.island.zubakha.services;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.map.GameMap;

import java.util.List;

public class AnimalMoveTask implements Runnable {
    @Override
    public void run() {
        List<Animal> animals = GameMap.
                getInstance().
                getAllAnimals().
                stream().
                filter(c -> c.getMaxSpeed() > 0).toList();
        for (Animal animal : animals) {
            animal.move();
        }
    }
}
