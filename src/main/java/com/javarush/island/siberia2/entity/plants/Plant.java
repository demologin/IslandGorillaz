package com.javarush.island.siberia2.entity.plants;

import com.javarush.island.siberia2.config.PlantSettings;
import com.javarush.island.siberia2.entity.Organism;
import com.javarush.island.siberia2.services.PlantGrowthService;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public abstract class Plant extends Organism {
    protected PlantSettings settings;
    protected final ReentrantLock lock = new ReentrantLock();
    private final PlantGrowthService growthService = new PlantGrowthService();

    public Plant(PlantSettings settings) {
        this.settings = settings;
    }

    @Override
    public void liveCycle() {
        grow();
    }

    public void die() {
        lock.lock();
        try {
            currentCell.removePlant(this);
        } finally {
            lock.unlock();
        }
    }

    public void grow() {
        lock.lock();
        try {
        growthService.grow(this);
        } finally {
            lock.unlock();
        }
    }

}