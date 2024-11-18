package com.javarush.island.levchuk.services;


import com.javarush.island.levchuk.entities.Entity;
import com.javarush.island.levchuk.map.Cell;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


public class ReproduceService {

    public void reproduceAllInCall(Cell cell) {
        cell.getResidents().values().forEach(residentsList -> {
            residentsList.forEach(entity -> {
                Entity childEntity = entity.reproduce(cell);
                if (childEntity != null) {
                    residentsList.add(childEntity);
                }
                resetReadinessForReproduction(cell.getResidents());
            });
        });

    }

    private void resetReadinessForReproduction(Map<Class<? extends Entity>, CopyOnWriteArrayList<Entity>> residents) {
        residents.values().stream()
                .filter(list -> !list.isEmpty())
                .flatMap(List::stream)
                .forEach(entity -> entity.setReproduced(false));
    }
}
