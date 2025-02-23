package com.javarush.island.zubakha.entity.herbivores;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Herbivore;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Buffalo extends Herbivore {

    public Buffalo() {
        super(700, 3, 100, 10, "Buffalo");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Buffalo){
            Cell cell = GameMap.
                    getInstance().
                    getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new Buffalo(), cell.getRow(), cell.getCol());
        }
    }
}
