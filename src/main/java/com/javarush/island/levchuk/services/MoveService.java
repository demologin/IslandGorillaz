package com.javarush.island.levchuk.services;

import com.javarush.island.levchuk.entities.Entity;
import com.javarush.island.levchuk.liveActions.Movable;
import com.javarush.island.levchuk.map.Cell;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class MoveService {
    public void moveAllInCall(Cell cell) {
        List<Movable> movables = cell.getResidents().values().stream()
                .flatMap(List::stream)
                .filter(Movable.class::isInstance)
                .map(Movable.class::cast)
                .collect(Collectors.toList());
        movables.forEach(movable -> movable.move(cell));
        }
    }
