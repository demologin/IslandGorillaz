package com.javarush.island.gerasimov.entity.map;
import lombok.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class GameMap {
    private final Cell[][] cells;
    public GameMap(int rows, int cols) {
        this.cells = new Cell[rows][cols];
    }
    public int getRows() {
        return cells.length;
    }
    public int getCols() {
        return cells[0].length;
    }

}
