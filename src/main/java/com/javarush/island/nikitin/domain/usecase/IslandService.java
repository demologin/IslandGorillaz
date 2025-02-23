package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.constants.FailMessagesDomain;
import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.exception.DomainException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class provides methods to fill the island
 * inhabited locations based on a given population prototype
 */
public class IslandService {
    private final Island island;
    private boolean hasInhabitedLocations;
    private final double percentFillingLocation;
    private final PopulationService populationService;

    public IslandService(Island island, PopulationService populationService, double percentFillingLocation) {
        this.island = island;
        this.populationService = populationService;
        this.percentFillingLocation = percentFillingLocation;
    }

    /**
     * Populates the island with populated locations based on the provided unit prototype repository
     */

    public void fillIslandWithInhabitedLocations() {
        Location[][] locations = island.getLocation();
        for (int y = 0; y < locations.length; y++) {
            for (int x = 0; x < locations[y].length; x++) {
                boolean beEmpty = resolveBeEmpty(percentFillingLocation);
                String localId = y + "_" + x;
                locations[y][x] = createLocation(beEmpty, localId);
            }
        }
        hasInhabitedLocations = true;
    }

    public Location[][] getHasInhabitedLocations() {
        if (hasInhabitedLocations) {
            return island.getLocation();
        }
        throw new DomainException(FailMessagesDomain.LOCATION_NOT_INITIALIZED);
    }

    private Location createLocation(boolean beEmpty, String localId) {
        Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> populations;
        if (beEmpty) {
            populations = populationService.createEmptyPopulationIntoLocation();
        } else {
            populations = populationService.createAllPopulationIntoLocation();
        }
        return new Location(populations, localId);
    }

    private boolean resolveBeEmpty(double percentFillingLocation) {
        return percentFillingLocation < ThreadLocalRandom.current().nextDouble();

    }
}

