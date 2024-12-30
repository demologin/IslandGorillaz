package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.exception.DomainException;
import com.javarush.island.nikitin.domain.exception.FailMessagesDomain;

import java.util.concurrent.ThreadLocalRandom;


public class IslandService {
    private final Island island;
    private boolean isInhabitedLocations;
    private final PopulationService populationService;

    public IslandService(Island island, PopulationService populationService) {
        this.island = island;
        this.populationService = populationService;
    }

    /**
     * Populates the island with populated locations based on the provided unit prototype repository
     */

    public void fillIslandWithInhabitedLocations(double fillingLimit) {
        Location[][] locations = island.getLocation();
        for (int y = 0; y < locations.length; y++) {
            for (int x = 0; x < locations[y].length; x++) {
                boolean beEmpty = beEmpty(fillingLimit);
                String localId = y + "_" + x;
                locations[y][x] = createLocation(beEmpty, localId);
            }
        }
        isInhabitedLocations = true;
    }

    public Location[][] getInhabitedLocations() {
        if (isInhabitedLocations) {
            return island.getLocation();
        }
        throw new DomainException(FailMessagesDomain.LOCATION_NOT_INITIALIZED);
    }

    private Location createLocation(boolean beEmpty, String localId) {
        if (beEmpty) {
            return new Location(populationService.createEmptyPopulation() , localId);
        }
        return new Location(populationService.createAllPopulation(), localId);
    }

    private boolean beEmpty(double fillingLimit) {
        return fillingLimit < ThreadLocalRandom.current().nextDouble();

    }
}
