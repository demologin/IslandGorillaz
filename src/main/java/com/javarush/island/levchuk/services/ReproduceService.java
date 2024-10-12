package com.javarush.island.levchuk.services;

import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.entities.Entity;
import com.javarush.island.levchuk.map.Cell;
import com.javarush.island.levchuk.utils.Randomizer;

import java.util.List;
import java.util.Set;

public class ReproduceService {
    public void reproduceTypeInCell(Cell cell, Entity entityParent) {
        List<Entity> potentialParents = cell.getResidents().get(entityParent.getClass());
        int numberOfParentPairs = potentialParents.size() / 2;
        for (int i = 0; i < numberOfParentPairs; i++) {
            if (!cell.checkFreeSpace(entityParent)) {
                break;
            }
            if (Randomizer.getRandomInt(100) >= Constants.CHANCE_OF_REPRODUCTION) {
                cell.addEntity(potentialParents.get(i).reproduce());
            }
        }
    }

    public void reproduceAllInCall(Cell cell, Entity entityParent) {
        Set<Class<? extends Entity>> typesParents = cell.getResidents().keySet();
        for (Class<? extends Entity> typesParent : typesParents) {
            if (!cell.getResidents().get(typesParent).isEmpty()) {
                cell.getResidents().get(typesParent).stream().forEach(entity -> reproduceTypeInCell(cell, entity));
            }
        }
    }
}
