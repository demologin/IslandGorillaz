package com.javarush.island.ivanov.api.entity;

import com.javarush.island.ivanov.entity.map.Cell;

@FunctionalInterface
public interface Eating {
    void eat(Cell currentCell);
}
