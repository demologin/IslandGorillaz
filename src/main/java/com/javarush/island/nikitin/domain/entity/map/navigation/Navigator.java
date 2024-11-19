package com.javarush.island.nikitin.domain.entity.map.navigation;

import com.javarush.island.nikitin.domain.entity.map.Location;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class Navigator {
    @Setter
    private Location[][] locations;
    private final Map<Location, Integer> mapRow = new HashMap<>();
    private final Map<Location, Integer> mapColumn = new HashMap<>();

    public void initialize() {
        for (int y = 0; y < locations.length; y++) {
            for (int x = 0; x < locations[y].length; x++) {
                var location = locations[y][x];
                mapRow.put(location, y);
                mapColumn.put(location, x);
            }
        }
    }

    public Location getLocation(int row, int col) {
        return locations[row][col];
    }

    public int getCurrentRow(Location habitat) {
        return mapRow.get(habitat);

    }

    public int getCurrentCol(Location habitat) {
        return mapColumn.get(habitat);
    }

    public int getCountRows() {
        return locations.length;
    }

    public int getCountCols() {
        return locations[0].length;
    }

    public Location execute(Direction direction, Location habitat, int stepRandom) {
        MoveStrategy strategy = direction.getStrategy();
        return strategy.findTargetLocation(this, habitat, stepRandom);
    }
}
