package com.javarush.island.zubakha.entity.herbivores;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Herbivore;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class WildBoar extends Herbivore {

    public WildBoar() {
        super(400, 2, 50, 50, "WildBoar");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Mouse" -> 0.5;
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof WildBoar){
            Cell cell = GameMap.
                    getInstance().
                    getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new WildBoar(), cell.getRow(), cell.getCol());
        }
    }
}
