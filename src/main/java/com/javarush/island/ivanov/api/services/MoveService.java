package com.javarush.island.ivanov.api.services;

import com.javarush.island.ivanov.api.entity.Movable;
import com.javarush.island.ivanov.entity.map.Cell;

import java.util.List;

public class MoveService {
    public void moveAllInCall(Cell cell) {
        cell.getResidents().values().stream()
                .flatMap(List::stream)
                .filter(Movable.class::isInstance)
                .map(Movable.class::cast)
                .forEach(movable -> {
                    movable.move(cell);
                });
    }
}
