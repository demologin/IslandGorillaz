package com.javarush.island.levchuk.services;

import com.javarush.island.levchuk.entities.Entity;
import com.javarush.island.levchuk.liveActions.Movable;
import com.javarush.island.levchuk.map.Cell;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class MoveService {
    public void moveAllInCall(Cell cell) {
        List<Class<? extends Entity>> entitiesTypes = cell.getResidents().keySet()
                .stream().filter(Movable.class ::isAssignableFrom).collect(Collectors.toList());
        for (Class<? extends Entity> entityClass : entitiesTypes) {
            if (!cell.getResidents().get(entityClass).isEmpty()){
                moveTypeInCell(cell.getResidents().get(entityClass), cell);
            }
        }
    }

    private void moveTypeInCell(List<Entity> entities, Cell cell) {
        ListIterator<Entity> iterator = entities.listIterator();
        while (iterator.hasNext()) {
            ((Movable) iterator.next()).move(cell);
        }
    }
}
