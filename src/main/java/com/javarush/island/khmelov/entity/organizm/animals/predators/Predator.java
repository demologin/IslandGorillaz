package com.javarush.island.khmelov.entity.organizm.animals.predators;

import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.map.Residents;
import com.javarush.island.khmelov.entity.organizm.Limit;
import com.javarush.island.khmelov.entity.organizm.animals.Animal;
import com.javarush.island.khmelov.exception.GameException;

import java.util.Map;

public abstract class Predator extends Animal {


    private final String bestTarget;

    public Predator(String name, String icon, Limit limit) {
        super(name, icon, limit);
        bestTarget = getFoodMap().stream()
                .sorted((t1, t2) -> t2.getValue() - t1.getValue())
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow(() -> new GameException("incorrect settings"));
    }

    @Override
    public boolean move(Cell startCell) {
        int maxStep = this
                .getLimit()
                .getMaxSpeed();
        Cell destinationCell = startCell;
        for (int i = 0; i < maxStep; i++) {
            destinationCell = startCell.getNextCell(1);
            Residents residents = destinationCell.getResidents();
            if (!residents.get(bestTarget).isEmpty()){
                break;
            }
        }
        return safeMove(startCell, destinationCell);
    }
}
