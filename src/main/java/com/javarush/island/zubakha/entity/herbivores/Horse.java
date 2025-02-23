package com.javarush.island.zubakha.entity.herbivores;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Herbivore;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Horse extends Herbivore {

    public Horse() {
        super(400, 4, 60, 20, "Horse");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Horse){
            Cell cell = GameMap.
                    getInstance().
                    getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new Horse(), cell.getRow(), cell.getCol());
        }
    }
}
