package com.javarush.island.khmelov.api.entity;

import com.javarush.island.khmelov.entity.map.Cell;

@FunctionalInterface
public interface Movable {

    @SuppressWarnings("UnusedReturnValue")
    boolean move(Cell startCell);

}
