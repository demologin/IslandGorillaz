package com.javarush.island.siberia2.entity.plants;

import com.javarush.island.siberia2.config.PlantSettings;
import com.javarush.island.siberia2.entity.Organism;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public abstract class Plant extends Organism {
    protected PlantSettings settings;
    protected final ReentrantLock lock = new ReentrantLock();

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

    public abstract void grow();

}