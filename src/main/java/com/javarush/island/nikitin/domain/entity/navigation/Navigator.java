package com.javarush.island.nikitin.domain.entity.navigation;

import com.javarush.island.nikitin.domain.constants.FailMessagesDomain;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.exception.DomainException;
import com.javarush.island.nikitin.domain.usecase.IslandService;

import java.util.HashMap;
import java.util.Map;

/**
 * The Navigator class is responsible for navigating through a two-dimensional array of Location objects.
 * It provides methods for retrieving and finding new locations
 * based on a given direction and movement strategy.
 * Perform initialization only after filling the array with locations {@link IslandService}
 */
public final class Navigator {
    private Location[][] locations;
    private final NavigatorCacheLocation navigatorCacheLocation;
    private final Map<Location, Integer> mapRow = new HashMap<>();
    private final Map<Location, Integer> mapColumn = new HashMap<>();

    public Navigator() {
        this.navigatorCacheLocation = new NavigatorCacheLocation();
    }

    public void initializeIslandMap(Location[][] locations) {
        if (this.locations != null) {
            throw new DomainException(FailMessagesDomain.IS_ALREADY_INITIALIZED);
        }
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

    public Location findNewLocation(Direction direction, Location habitat, int stepRandom) {
        return navigatorCacheLocation.cacheContains(direction, habitat, stepRandom,
                () -> this.computeNewLocation(direction, habitat, stepRandom));
    }

    public void warmUpCache(int stepWarmUp) {
        for (Location[] location : locations) {
            for (Location simpleLocation : location) {
                checkLocationNotNull(simpleLocation);
                for (Direction value : Direction.values()) {
                    for(int i = 0; i < stepWarmUp; i++){
                        findNewLocation(value, simpleLocation, stepWarmUp);
                    }
                }
            }
        }
    }

    private Location computeNewLocation(Direction direction, Location habitat, int stepRandom) {
        MoveStrategy strategy = direction.getStrategy();
        return strategy.findTargetLocation(this, habitat, stepRandom);
    }

    private void checkLocationNotNull(Location location) {
        if (location == null) {
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
}
