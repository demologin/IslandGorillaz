package com.javarush.island.zubakha.entity.predators;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Predator;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Bear extends Predator {

    public Bear() {
        super(500, 2, 80, 5, "Bear");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Buffalo" -> 0.2;
            case "Horse" -> 0.4;
            case "WildBoar" -> 0.5;
            case "Goat", "Sheep" -> 0.7;
            case "Deer", "Rabbit", "Snake" -> 0.8;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Bear){
            Cell cell = GameMap.
                    getInstance().
                    getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new Bear(), cell.getRow(), cell.getCol());
        }
    }
}
