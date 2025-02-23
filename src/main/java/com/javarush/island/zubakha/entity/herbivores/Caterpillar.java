package com.javarush.island.zubakha.entity.herbivores;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Herbivore;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        super(0.01, 0, 0, 1000, "Caterpillar");
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Caterpillar){
            Cell cell = GameMap.
                    getInstance().
                    getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new Caterpillar(), cell.getRow(), cell.getCol());
        }
    }
}