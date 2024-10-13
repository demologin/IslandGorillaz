package com.javarush.island.levchuk.map;

import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.utils.MapInitializer;
import lombok.Getter;

@Getter
public class IslandMap {
    private final Cell[][] islandMap;

    public IslandMap(int row, int col) {
        islandMap = new Cell[row][col];
    }
    public void initMap(MapInitializer mapInitializer) {
        mapInitializer.createEmptyMap(this.islandMap);
        mapInitializer.fillMapEntities(this.islandMap);
        
    }
    
}