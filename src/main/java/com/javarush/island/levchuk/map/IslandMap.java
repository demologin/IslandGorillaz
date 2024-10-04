package com.javarush.island.levchuk.map;

import com.javarush.island.levchuk.constants.Constants;

public class IslandMap {
    public static Cell[][] map;

    public IslandMap() {
        map = new Cell[Constants.DEFAULT_MAP_ROW][Constants.DEFAULT_MAP_COL];
    }

    IslandMap (int row, int col ) {
        map = new Cell[row][col];
    }

    public void initializeMap(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Cell(i,j);
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].findNeighbors(map);
            }
        }

    }
}
