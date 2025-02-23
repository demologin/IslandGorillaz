package com.javarush.island.nikitin.presentation.services;

import com.javarush.island.nikitin.application.entity.DataContainer;
import com.javarush.island.nikitin.domain.entity.map.Location;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DataProcessing {

    public Map<String, Integer> composeStatistics(DataContainer data) {
        Location[][] locations = data.locations();
        return Arrays.stream(locations)
                .parallel()
                .flatMap(Arrays::stream)
                .map(Location::getAllPopulationsLocation)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().size(),
                        Integer::sum,
                        HashMap::new));
    }

    public int countAllResidents(Map<String, Integer> map) {
        return map.values().stream().mapToInt(value -> value).sum();
    }
}
