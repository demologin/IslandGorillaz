package com.javarush.island.zubakha.entity.predators;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Predator;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Snake extends Predator {
    public Snake() {
        super(15, 1, 3, 30, "Snake");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Fox" -> 0.15;
            case "Rabbit" -> 0.2;
            case "Mouse" -> 0.4;
            default -> 0;
        };
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Snake) {
            Cell cell = GameMap.
                    getInstance().
                    getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new Snake(), cell.getRow(), cell.getCol());
        }
    }
}