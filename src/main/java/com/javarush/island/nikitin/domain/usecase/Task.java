package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Location;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class Task implements Callable<Long> {
    private final Location habitat;
    private final int currentCalendarDay;
    private  final Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> allPopulations;

    public Task(Location habitat, int currentCalendarDay) {
        this.habitat = habitat;
        this.currentCalendarDay = currentCalendarDay;
        this.allPopulations = habitat.getAllPopulationsLocation();
    }

    @Override
    public Long call() {
        allPopulations.forEach((key, value) -> value
                .forEach(unit -> unit.survive(habitat, currentCalendarDay)));
        return habitat.getDeathTotal();
    }
}
