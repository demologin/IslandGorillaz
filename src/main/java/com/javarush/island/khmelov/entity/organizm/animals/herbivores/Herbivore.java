package com.javarush.island.khmelov.entity.organizm.animals.herbivores;

import com.javarush.island.khmelov.entity.organizm.Limit;
import com.javarush.island.khmelov.entity.organizm.animals.Animal;

public abstract class Herbivore extends Animal {
    
    public Herbivore(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

//    @Override
//    public boolean move(Cell startCell) {
//        int maxStep = this
//                .getLimit()
//                .getMaxSpeed();
//        Cell destinationCell = startCell;
//        for (int i = 0; i < maxStep; i++) {
//            destinationCell = destinationCell.getNextCell(1);
//            Residents residents = destinationCell.getResidents();
//            if (!residents.get("Grass").isEmpty()){
//                break;
//            }
//        }
//        return safeMove(startCell, destinationCell);
//    }
}
