package com.javarush.island.popov.map;

import com.javarush.island.popov.units.Unit;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class IslandMap {
    @Getter
    private final Cell[][] cells;

    public IslandMap(int y, int x) {
        this.cells = new Cell[y][x];
    }

    public int getY() {
        return cells.length;
    }

    public int getX(){
            return cells[0].length;
    }
}

