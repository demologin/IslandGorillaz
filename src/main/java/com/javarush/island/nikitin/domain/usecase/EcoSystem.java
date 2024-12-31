package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.entity.map.Location;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class EcoSystem {
    private final Island island;
    @Getter
    private int startDate;

    public EcoSystem(Island island, int startDate) {
        this.island = island;
        this.startDate = startDate;
    }

    public List<Runnable> act() {
        List<Runnable> tasks = new ArrayList<>();
        Location[][] locations = island.getLocation();
        for (Location[] location : locations) {
            for (Location habitat : location) {
                int currentStartDate = startDate;
                Runnable task = () -> iteratePopulation(habitat.getPopulations(), habitat, currentStartDate);
                tasks.add(task);
            }
        }
        startDate++;
        return tasks;
    }

    private void iteratePopulation(Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> population, Location habitat, int startDate) {
        population.forEach((key, value) -> value
                .forEach(unit -> unit.survive(habitat, startDate)));
    }

    public Location[][] getLocationsForView() {
        return island.getLocation();
    }
}
