package com.javarush.island.zubakha.entity.predators;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Predator;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Eagle extends Predator {

    public Eagle() {
        super(6, 3, 1, 20, "Eagle");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Fox" -> 0.1;
            case "Duck" -> 0.8;
            case "Rabbit", "Mouse" -> 0.9;
            default -> 0;
        };
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Eagle){
            Cell cell = GameMap.
                    getInstance().
                    getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new Eagle(), cell.getRow(), cell.getCol());
        }
    }
}