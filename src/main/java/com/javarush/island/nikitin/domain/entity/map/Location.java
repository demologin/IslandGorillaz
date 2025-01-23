package com.javarush.island.nikitin.domain.entity.map;


import com.javarush.island.nikitin.domain.constants.LogMessagesDmn;
import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.util.Biotas;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Location {
    @EqualsAndHashCode.Include
    private final String localId;
    private final AtomicLong DEAD_COUNTER = new AtomicLong();
    private static final Logger LOGGER = LoggerFactory.getLogger(Location.class);
    @Getter
    private long deathTotal;
    @Getter
    private final Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> allPopulationsLocation;

    public Location(Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> allPopulationsLocation, String localId) {
        this.allPopulationsLocation = allPopulationsLocation;
        this.localId = localId;
    }

    public boolean buryInGrave(Biota unit) {
        boolean isAccess = removeUnitLocation(unit);
        if (isAccess) {
            this.deathTotal = DEAD_COUNTER.incrementAndGet();
            LOGGER.debug(LogMessagesDmn.DEATH_LOCATION_UNIT_COUNT, this, unit, deathTotal);
        }
        return isAccess;
    }

    public boolean removeUnitLocation(Biota unit) {
        var population = getPopulationByName(Biotas.sayMyNameCommunity(unit));
        return population.remove(unit);
    }

    public boolean addUnitLocation(Biota unit) {
        String nameCommunity = Biotas.sayMyNameCommunity(unit);
        ConcurrentHashMap.KeySetView<Biota, Boolean> populationByName = getPopulationByName(nameCommunity);

        if (checkFreeSpace(unit)) {
            return populationByName.add(unit);
        }
        return false;
    }

    public ConcurrentHashMap.KeySetView<Biota, Boolean> getPopulationByName(String nameCommunity) {
        return allPopulationsLocation.get(nameCommunity);
    }

    public Optional<Biota> getRandomBiotaByNameCommunity(String nameCommunity) {
        Optional<ConcurrentHashMap.KeySetView<Biota, Boolean>> populationByName = Optional.ofNullable(allPopulationsLocation.get(nameCommunity));

        return populationByName.filter(set -> !set.isEmpty())
                .flatMap(Location::getBiota);
    }

    private static Optional<Biota> getBiota(ConcurrentHashMap.KeySetView<Biota, Boolean> set) {
        int sizeSet = set.size();
        if (sizeSet > 0) {
            int random = ThreadLocalRandom.current().nextInt(sizeSet);
            return set.stream()
                    .skip(random)
                    .findFirst();
        }
        return Optional.empty();
    }

    public boolean isPresentPopulation(String nameCommunity) {
        return allPopulationsLocation.containsKey(nameCommunity);
    }

    public boolean checkPartner(String nameCommunity, int minPartner) {
        return sizePopulation(nameCommunity) > minPartner;
    }

    private boolean checkFreeSpace(Biota unit) {
        String nameCommunity = Biotas.sayMyNameCommunity(unit);
        int maxCountUnit = unit.getLimitData().maxCountUnit();
        return sizePopulation(nameCommunity) < maxCountUnit;
    }

    private int sizePopulation(String nameCommunity) {
        return getPopulationByName(nameCommunity).size();
    }

    @Override
    public String toString() {
        return "Location{" +
                "localId='" + localId + '\'' +
                '}';
    }
}
