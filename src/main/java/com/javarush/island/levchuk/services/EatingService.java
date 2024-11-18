package com.javarush.island.levchuk.services;

import com.javarush.island.levchuk.entities.Entity;
import com.javarush.island.levchuk.liveActions.Eating;
import com.javarush.island.levchuk.map.Cell;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class EatingService {
    public void eatAllInCell(Cell cell) {
        Map<Class<? extends Entity>, CopyOnWriteArrayList<Entity>> entities = cell.getResidents();
        for (Map.Entry<Class<? extends Entity>, CopyOnWriteArrayList<Entity>> entitiesList : entities.entrySet()) {
            List<Entity> animals = entitiesList.getValue()
                    .stream().filter(Eating.class::isInstance)
                    .collect(Collectors.toList());
            ListIterator<Entity> iterator = animals.listIterator();
            while (iterator.hasNext()) {
                Eating animal = (Eating) iterator.next();
                animal.eat(cell);
            }
        }
    }
}
