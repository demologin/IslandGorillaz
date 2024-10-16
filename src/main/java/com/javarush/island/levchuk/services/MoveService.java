package com.javarush.island.levchuk.services;


import com.javarush.island.levchuk.liveActions.Movable;
import com.javarush.island.levchuk.map.Cell;
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
