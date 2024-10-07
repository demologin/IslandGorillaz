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
    private final List<Organism> organisms = new CopyOnWriteArrayList<>();


    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean canAddOrganism(Organism organism) {

    }

    public void addOrganism(Organism organism) {

    }

    public void removeOrganism(Organism organism) {

    }

}
