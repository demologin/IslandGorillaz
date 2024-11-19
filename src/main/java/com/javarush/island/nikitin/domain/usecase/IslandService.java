package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.entity.map.navigation.Navigator;
import com.javarush.island.nikitin.domain.repository.RegistryProto;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class IslandService {
    @Setter
    private Island island;
    private Navigator navigator;

    public void setupNavigation(Navigator navigator) {
        this.navigator = navigator;
        navigator.setLocations(island.getLocation());
    }

    public void fillIsland(RegistryProto repository, double fillingLimit) {
        Location[][] locations = island.getLocation();
        for (int y = 0; y < locations.length; y++) {
            for (int x = 0; x < locations[y].length; x++) {
                var registry = repository.registry();
                boolean beEmpty = beEmpty(fillingLimit);
                locations[y][x] = createLocation(registry, beEmpty);
            }
        }
    }

    private Location createLocation(Map<String, Biota> registry, boolean beEmpty) {
        if(beEmpty){
            return new Location(createEmptyPopulation(registry));
        }
        return new Location(createAllPopulation(registry));
    }

    private Map<String, Set<Biota>> createEmptyPopulation(Map<String, Biota> registry) {
        return registry.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, v -> new HashSet<>()));
    }

    private Map<String, Set<Biota>> createAllPopulation(Map<String, Biota> registry) {
        return registry.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, e -> createSinglePopulation(e.getValue())));
    }

    private Set<Biota> createSinglePopulation(Biota proto) {
        int maxCountPopulation = proto.getProps().getMaxCountUnit();
        int limiter = ThreadLocalRandom.current().nextInt(maxCountPopulation);

        return IntStream.range(0, limiter)
                .mapToObj(count -> proto.clone())
                .collect(Collectors.toCollection(HashSet::new));
    }

    private boolean beEmpty(double fillingLimit) {
        return fillingLimit < ThreadLocalRandom.current().nextDouble();

    }
}
