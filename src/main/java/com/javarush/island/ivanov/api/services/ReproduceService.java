package com.javarush.island.ivanov.api.services;

import com.javarush.island.ivanov.entity.map.Cell;
import com.javarush.island.ivanov.entity.organism.Organism;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReproduceService {
    public void reproduceAllInCall(Cell cell) {
        cell.getResidents().values().forEach(residentsList -> {
            residentsList.forEach(entity -> {
                Organism childEntity = entity.reproduce(cell);
                if (childEntity != null) {
                    residentsList.add(childEntity);
                }
                resetReadinessForReproduction(cell.getResidents());
            });
        });

    }

    private void resetReadinessForReproduction(Map<Class<? extends Organism>, CopyOnWriteArrayList<Organism>> residents) {
        residents.values().stream()
                .filter(list -> !list.isEmpty())
                .flatMap(List::stream)
                .forEach(entity -> entity.setReproduced(false));
    }
}
