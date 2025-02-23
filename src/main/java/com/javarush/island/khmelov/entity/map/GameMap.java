package com.javarush.island.khmelov.entity.map;

import com.javarush.island.khmelov.entity.organizm.Organisms;

import java.util.stream.Collectors;

public class GameMap {
    private final Cell[][] cells;

    public GameMap(int rows, int cols) {
        this.cells = new Cell[rows][cols];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getRows() {
        return cells.length;
    }

    public int getCols() {
        return cells[0].length;
    }

    public Organisms getAll() {
        Organisms all = new Organisms();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                all.addAll(cell.getResidents()
                        .values()
                        .stream()
                        .flatMap(Organisms::stream)
                        .collect(Collectors.toSet()));
            }
        }
        return all;
    }

}
