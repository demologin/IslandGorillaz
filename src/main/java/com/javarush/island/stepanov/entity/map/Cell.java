package com.javarush.island.stepanov.entity.map;

import com.javarush.island.stepanov.entity.oganism.Organism;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {

    private final List<Cell> nextCell = new ArrayList<>();
    private final Map<String, Lock> keyLocks = new HashMap<>();
    private final Map<String, List<Organism>> residentMap = new HashMap<>();
    @Getter
    public final SortedByValueTreeMap<String, Integer> populationStatistics = new SortedByValueTreeMap<>();

    public Set<String> getOrganismsSet(){
        Set<String> organismsSet = residentMap.keySet();
        return organismsSet;
    }

    public void addOrganism(String key, Organism organism) {
        Lock keyLock = getKeyLock(key);
        keyLock.lock();
        try {
            residentMap.computeIfAbsent(key, k -> new ArrayList<>()).add(organism);
        } finally {
            keyLock.unlock();
        }
    }

    public List<Organism> getOrganisms(String key) {
        Lock keyLock = getKeyLock(key);
        keyLock.lock();
        try {
            return residentMap.getOrDefault(key, Collections.emptyList());
        } finally {
            keyLock.unlock();
        }
    }

    private Lock getKeyLock(String key) {
        synchronized (keyLocks) {
            return keyLocks.computeIfAbsent(key, k -> new ReentrantLock(true));
        }
    }
}
