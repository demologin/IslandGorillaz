package com.javarush.island.zubakha.entity.herbivores;


import com.javarush.island.zubakha.entity.Animal;
import com.javarush.island.zubakha.entity.Herbivore;
import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;

public class Deer extends Herbivore {

        public Deer() {
            super(300, 4, 50, 20, "Deer");
        }

        @Override
        public void multiply(Animal partner) {
            if (partner instanceof Deer){
                Cell cell = GameMap.
                        getInstance().
                        getLocation(partner.getRow(), partner.getCol());
                GameMap.
                        getInstance().
                        addAnimal(new Deer(), cell.getRow(), cell.getCol());
            }
        }
    }

