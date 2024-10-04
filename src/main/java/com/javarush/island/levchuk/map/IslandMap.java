package com.javarush.island.levchuk.map;

public class IslandMap {
    public static Cell[][] islandMap;

    IslandMap ( int width, int height ) {
        islandMap = new Cell[width][height];
    }

    private void initializeMap(){
        for (int i = 0; i < islandMap.length; i++) {
            for (int j = 0; j < islandMap[i].length; j++) {
                Cell cell = new Cell(i,j);

            }
        }
    }
}
