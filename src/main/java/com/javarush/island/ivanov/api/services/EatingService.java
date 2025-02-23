package com.javarush.island.ivanov.api.services;

import com.javarush.island.ivanov.api.entity.Eating;
import com.javarush.island.ivanov.entity.map.Cell;
import com.javarush.island.ivanov.entity.organism.Organism;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class EatingService {
    public void eatAllInCell(Cell cell) {
        Map<Class<? extends Organism>, CopyOnWriteArrayList<Organism>> entities = cell.getResidents();
        for (Map.Entry<Class<? extends Organism>, CopyOnWriteArrayList<Organism>> entitiesList : entities.entrySet()) {
            List<Organism> animals = entitiesList.getValue()
                    .stream().filter(Eating.class::isInstance)
                    .collect(Collectors.toList());
            ListIterator<Organism> iterator = animals.listIterator();
            while (iterator.hasNext()) {
                Eating animal = (Eating) iterator.next();
                animal.eat(cell);
            }
        }
    }

}
