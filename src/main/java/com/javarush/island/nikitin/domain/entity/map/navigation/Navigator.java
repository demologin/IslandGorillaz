package com.javarush.island.nikitin.domain.entity.map.navigation;

import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.exception.DomainException;
import com.javarush.island.nikitin.domain.exception.FailMessagesDomain;
import com.javarush.island.nikitin.domain.usecase.IslandService;

import java.util.HashMap;
import java.util.Map;

/**
 * The Navigator class is responsible for navigating through a two-dimensional array of Location objects.
 * It provides methods for retrieving and finding new locations
 * based on a given direction and movement strategy.
 * Perform initialization only after filling the array with locations {@link IslandService}
 */
public class Navigator {
    private Location[][] locations;
    private final Map<Location, Integer> mapRow = new HashMap<>();
    private final Map<Location, Integer> mapColumn = new HashMap<>();

    public void initializeIslandMap(Location[][] locations) {
        this.locations = locations;
        for (int y = 0; y < locations.length; y++) {
            for (int x = 0; x < locations[y].length; x++) {
                var location = locations[y][x];
                checkLocationNotNull(location);
                mapRow.put(location, y);
                mapColumn.put(location, x);
            }
        }
    }

    private void checkLocationNotNull(Location location){
        if(location == null){
            throw new DomainException(FailMessagesDomain.LOCATION_NOT_INITIALIZED);
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

    public Location findNewLocation(Direction direction, Location habitat, int stepRandom) {
        MoveStrategy strategy = direction.getStrategy();
        return strategy.findTargetLocation(this, habitat, stepRandom);
    }
}
