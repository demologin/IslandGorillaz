package com.javarush.island.gerasimov.entity.map;
import lombok.Data;
import lombok.ToString;

@Data
public class GameMap {
    private final Cell[][] cells;
}
