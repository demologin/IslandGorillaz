package com.javarush.island.zubakha.entity.herbivores;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Herbivore;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Duck extends Herbivore {

    public Duck() {
        super(1, 4, 0.15, 200, "Duck");
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
        if (partner instanceof Duck){
            Cell cell = GameMap.getInstance().getLocation(partner.getRow(), partner.getCol());
            GameMap.getInstance().addAnimal(new Duck(), cell.getRow(), cell.getCol());
        }
    }
}
