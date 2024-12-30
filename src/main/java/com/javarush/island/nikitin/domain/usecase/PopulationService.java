package com.javarush.island.nikitin.domain.usecase;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.repository.RegistryProto;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Setter
public class PopulationService {
    private final Boolean defaultValueIntoMap = Boolean.TRUE;
    private RegistryProto registryProto;

    public Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> createEmptyPopulation() {
        return registryProto.registry().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        v -> new ConcurrentHashMap<Biota, Boolean>().keySet(defaultValueIntoMap)));
    }

    public Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> createAllPopulation() {
        return registryProto.registry().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, e -> createSinglePopulation(e.getValue())));
    }

    private ConcurrentHashMap.KeySetView<Biota, Boolean> createSinglePopulation(Biota proto) {
        int limiter = maxCountPopulation(proto);
        return IntStream.range(0, limiter)
                .mapToObj(count -> proto.clone())
                .collect(Collectors.toConcurrentMap(
                        biota -> biota,
                        biota -> defaultValueIntoMap,
                        (first, second) -> first,
                        ConcurrentHashMap::new))
                .keySet(defaultValueIntoMap);
    }

    private int maxCountPopulation(Biota proto) {
        int bound = 1;
        int maxCountPopulation = proto.getLimitData().maxCountUnit() + bound;
        return ThreadLocalRandom.current().nextInt(maxCountPopulation);
    }
}
