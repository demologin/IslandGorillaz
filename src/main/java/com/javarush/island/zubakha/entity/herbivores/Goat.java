package com.javarush.island.zubakha.entity.herbivores;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Herbivore;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Goat extends Herbivore {

    public Goat() {
        super(60, 3, 10, 140, "Goat");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Goat){
            Cell cell = GameMap.
                    getInstance().
                    getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new Goat(), cell.getRow(), cell.getCol());
        }
    }
}
