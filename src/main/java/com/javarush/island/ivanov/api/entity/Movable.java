package com.javarush.island.ivanov.api.entity;

import com.javarush.island.ivanov.entity.map.Cell;

@FunctionalInterface
public interface Movable {

    void move(Cell startCell);
}
