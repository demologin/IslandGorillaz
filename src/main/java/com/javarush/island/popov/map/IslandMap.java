package com.javarush.island.popov.map;
import lombok.Getter;


@Getter
public class IslandMap {
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

