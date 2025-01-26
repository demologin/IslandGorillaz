package com.javarush.island.stepanov.entity.map;

import com.javarush.island.stepanov.entity.oganism.Organism;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
    @Getter
    private final List<Cell> nextCell = new ArrayList<>();
    private final Map<String, Lock> keyLocks = new HashMap<>();
    private final Map<String, List<Organism>> residentMap = new HashMap<>();
    @Getter
    public final SortedByValueTreeMap<String, Integer> populationStatistics = new SortedByValueTreeMap<>();

    public Set<String> getOrganismsSet(){
        Set<String> organismsSet = residentMap.keySet();
        return organismsSet;
    }

    public void addOrganism(Organism organism) {
        String key = organism.getName();
        Lock keyLock = getKeyLock(key);
        keyLock.lock();
        try {
            residentMap.computeIfAbsent(key, k -> new ArrayList<>()).add(organism);
        } finally {
            keyLock.unlock();
        }
    }

    public void removeOrganism(Organism organism) {
        String key = organism.getName();
        Lock keyLock = getKeyLock(key);
        keyLock.lock();
        try {
            List<Organism> organismList = residentMap.get(key);
            organismList.remove(organism);
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
