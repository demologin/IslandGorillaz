package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.repository.RegistryProto;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class provides methods for creating empty and full populations
 * based on data stored in the registry.
 */
@Setter
public class PopulationService {
    private final Boolean defaultValueIntoMap = Boolean.TRUE;
    private RegistryProto registryProto;

    public Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> createEmptyPopulationIntoLocation() {
        Map<String, Biota> registry = registryProto.registry();
        return registry.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        v -> new ConcurrentHashMap<Biota, Boolean>().keySet(defaultValueIntoMap)));
    }

    public Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> createAllPopulationIntoLocation() {
        Map<String, Biota> registry = registryProto.registry();
        return registry.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> createSinglePopulation(e.getValue())));
    }

    private ConcurrentHashMap.KeySetView<Biota, Boolean> createSinglePopulation(Biota proto) {
        int limiter = maxCountPopulation(proto);
        ConcurrentHashMap<Biota, Boolean> collect = IntStream.range(0, limiter)
                .mapToObj(count -> proto.clone())
                .collect(Collectors.toConcurrentMap(
                        biota -> biota,
                        biota -> defaultValueIntoMap,
                        (firstValue, secondValue) -> firstValue,
                        ConcurrentHashMap::new));
        return collect.keySet(defaultValueIntoMap);
    }

    private int maxCountPopulation(Biota proto) {
        int lowerBound = 1;
        int maxCountPopulation = proto.getLimitData().maxCountUnit();
        int randomCount = ThreadLocalRandom.current().nextInt(maxCountPopulation);
        return randomCount + lowerBound;
    }
}
