package com.javarush.island.nikitin.domain.entity.map;


import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.exception.DomainException;
import com.javarush.island.nikitin.domain.exception.FaultMessage;
import lombok.Getter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Location {
    @Getter
    private final Map<String, Set<Biota>> populations;

    public Location(Map<String, Set<Biota>> population) {
        this.populations = population;
    }

    public void removeUnitLocation(Biota unit) {

        var population = getPopulationByName(unit.sayMyNameCommunity());
        //захватить лок и удалить юнита
        population.remove(unit);
    }

    //todo проверяет наличие свободного места в популяции, хватает лок и добавляется. Возвращает true or false
    public boolean addUnitLocation(Biota unit) {
        String nameCommunity = unit.getClass().getSimpleName();
        if (checkSpace(nameCommunity, unit.getProps().getMaxCountUnit())) {
            var population = getPopulationByName(nameCommunity);
            //захватить лок и добавить юнита
            population.add(unit);


            return true;
        }
        return false;
    }


    public Set<Biota> getMaskPopulationByName(String nameCommunity) {
        if (!populations.containsKey(nameCommunity)) {
            throw new DomainException(FaultMessage.LOCATION + nameCommunity);
        }
        return new HashSet<>(getPopulationByName(nameCommunity));
    }

    public Set<Biota> getPopulationByName(String nameCommunity) {
        return populations.get(nameCommunity);
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
