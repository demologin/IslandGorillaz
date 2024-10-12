package com.javarush.island.levchuk.map;

import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.utils.MapInitializer;
import lombok.Getter;
import lombok.Setter;


public class IslandMap {
    @Getter
    @Setter
    private static Cell[][] islandMap;

    public IslandMap() {
        islandMap = new Cell[Constants.DEFAULT_MAP_ROW][Constants.DEFAULT_MAP_COL];
    }

    public IslandMap(int row, int col) {
        islandMap = new Cell[row][col];
    }

    public void initMap(MapInitializer mapInitializer) {
        mapInitializer.createEmptyMap(this.islandMap);
        mapInitializer.fillMapEntities(this.islandMap);
    }
}
