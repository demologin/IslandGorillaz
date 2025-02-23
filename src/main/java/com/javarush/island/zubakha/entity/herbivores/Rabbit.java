package com.javarush.island.zubakha.entity.herbivores;

import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Herbivore;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super(2, 2, 0.45, 150, "Rabbit");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Rabbit){
            Cell cell = GameMap.
                    getInstance().getLocation(partner.getRow(), partner.getCol());
            GameMap.
                    getInstance().
                    addAnimal(new Rabbit(), cell.getRow(), cell.getCol());
        }
    }
}
