package com.javarush.island.nikitin.domain.entity.map;


import com.javarush.island.nikitin.domain.entity.biota.Biota;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Location {
    public String localId;
    private final Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> populations;

    public Location(Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> population, String localId) {
        this.populations = population;
        this.localId = localId;
    }

    public boolean removeUnitLocation(Biota unit) {
        var population = getPopulationByName(unit.sayMyNameCommunity());
        return population.remove(unit);
    }

    //todo проверяет наличие свободного места в популяции, хватает лок и добавляется. Возвращает true or false
    public boolean addUnitLocation(Biota unit) {
        String nameCommunity = unit.sayMyNameCommunity();
        ConcurrentHashMap.KeySetView<Biota, Boolean> populationByName = getPopulationByName(nameCommunity);
        if (checkSpace(nameCommunity, unit.getLimitData().maxCountUnit())) {
            populationByName.add(unit);
            return true;
        }
        return false;
    }

    public ConcurrentHashMap.KeySetView<Biota, Boolean> getPopulationByName(String nameCommunity) {
        return populations.get(nameCommunity);
    }

    public Optional<Biota> getRandomBiotaByNameCommunity(String nameCommunity) {
        Optional<ConcurrentHashMap.KeySetView<Biota, Boolean>> populationByName = Optional.ofNullable(populations.get(nameCommunity));

        return populationByName
                .filter(set -> !set.isEmpty())
                .flatMap(set -> {
                    int sizeSet = set.size();
                    if (sizeSet > 0) {
                        int random = ThreadLocalRandom.current().nextInt(sizeSet);
                        return set.stream()
                                .skip(random)
                                .findFirst();
                    }
                    return Optional.empty();
                });
    }

    public boolean isPresentPopulation(String nameCommunity) {
        return populations.containsKey(nameCommunity);
    }

    private boolean checkSpace(String nameCommunity, int maxCountUnit) {
        return sizePopulation(nameCommunity) < maxCountUnit;
    }

    public boolean checkPartner(String nameCommunity) {
        int minPartner = 1;
        return sizePopulation(nameCommunity) > minPartner;
    }

    private int sizePopulation(String nameCommunity) {
        return getPopulationByName(nameCommunity).size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (var m : populations.entrySet()) {
            str.append("\t").append(m.getKey()).append(": ").append(m.getValue().size()).append("\n");
        }
        return "population=" + "{" + "\n" + str +
                '}';
    }
}
