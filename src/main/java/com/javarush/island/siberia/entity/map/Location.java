package com.javarush.island.siberia.entity.map;

import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.organism.Organism;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Location {
    private final int x;
    private final int y;
    private final ReentrantLock lock = new ReentrantLock();
    private final List<Organism> organisms = new CopyOnWriteArrayList<>(); //todo для теста скорости. если что заменить
    private final Map<String, Integer> organismCounts = new ConcurrentHashMap<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean canAddOrganism(Organism organism) {
        String species = organism.getClass().getSimpleName();
        int maxCount = Settings.getInstance().getOrganismSettings(species).getMaxCountPerCell();
        int currentCount = organismCounts.getOrDefault(species, 0);
        return currentCount < maxCount;
    }

    public void addOrganism(Organism organism) {
        lock.lock();
        try {
            if (canAddOrganism(organism)) {
                organisms.add(organism);
                String species = organism.getClass().getSimpleName();
                organismCounts.merge(species, 1, Integer::sum);
                organism.setLocation(this);
            }
        } finally {
            lock.unlock();
        }
    }

    public void removeOrganism(Organism organism) {
        lock.lock();
        try {
            organisms.remove(organism);
            String species = organism.getClass().getSimpleName();
            organismCounts.merge(species, -1, Integer::sum);
            if (organismCounts.get(species) <= 0) {
                organismCounts.remove(species);
            }
        } finally {
            lock.unlock();
        }
    }


}