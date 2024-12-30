package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.entity.map.Location;
import lombok.Getter;

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

    //TODO метод запускает перебор локаций на острове и для каждого животного запуск цепочки действия
    public void act() {
        Location[][] locations = island.getLocation();
        for (Location[] location : locations) {
            for (Location habitat : location) {
                iteratePopulation(habitat.getPopulations(), habitat);
            }
        }
        startDate++;
    }

    //todo раньше использовалась маска коллекции для обхода
    private void iteratePopulation(Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> population, Location habitat) {
        population.entrySet().forEach((entry) -> {

            entry.getValue().forEach(unit -> {
                unit.survive(habitat, startDate);
            });
        });
    }

    public Location[][] getLocationsForView() {
        return island.getLocation();
    }
}
