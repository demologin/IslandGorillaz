package com.javarush.island.levchuk.map;
import lombok.Getter;

@Getter
public class IslandMap {
    private final Cell[][] islandMap;

    public IslandMap(int row, int col) {
        islandMap = new Cell[row][col];
    }
    
}