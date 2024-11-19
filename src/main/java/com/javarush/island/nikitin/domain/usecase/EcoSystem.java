package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.entity.map.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Setter
@Getter
public class EcoSystem {
    private Island island;
    private int generalDay;

    //TODO метод запускает перебор локаций на острове и для каждого животного запуск цепочки действия
    public void act() {
        Location[][] locations = island.getLocation();
        for (Location[] location : locations) {
            for (Location habitat : location) {
                iteratePopulation(habitat.getPopulations(), habitat);
            }
        }
        generalDay++;
    }

    private void iteratePopulation(Map<String, Set<Biota>> population, Location habitat) {
        for (var entry : population.entrySet()) {
            habitat.getMaskPopulationByName(entry.getKey())
                    .forEach(unit -> unit.survive(habitat, generalDay));
        }
    }
}
