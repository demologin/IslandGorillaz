package com.javarush.island.zubakha.entity.predators;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Predator;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Fox extends Predator {

    public Fox() {
        super(8, 2, 2, 30, "Fox");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.4;
            case "Duck" -> 0.6;
            case "Rabbit" -> 0.7;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Fox){
            Cell cell = GameMap.
                    getInstance().
                    getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new Fox(), cell.getRow(), cell.getCol());
        }
    }
}