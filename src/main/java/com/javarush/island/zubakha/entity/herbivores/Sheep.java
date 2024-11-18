package com.javarush.island.zubakha.entity.herbivores;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Herbivore;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Sheep extends Herbivore {

    public Sheep() {
        super(70, 3, 15, 140, "Sheep");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Sheep){
            Cell cell = GameMap.
                    getInstance().
                    getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new Sheep(), cell.getRow(), cell.getCol());
        }
    }
}
