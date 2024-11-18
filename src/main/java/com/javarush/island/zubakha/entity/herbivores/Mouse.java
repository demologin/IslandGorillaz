package com.javarush.island.zubakha.entity.herbivores;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Herbivore;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Mouse extends Herbivore {

    public Mouse() {
        super(0.05, 1, 0.01, 500, "Mouse");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Mouse){
            Cell cell = GameMap.
                    getInstance().
                    getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new Mouse(), cell.getRow(), cell.getCol());
        }
    }
}
