package com.javarush.island.siberia2.entity.animals;


import com.javarush.island.siberia2.api.Eatable;
import com.javarush.island.siberia2.api.Movable;
import com.javarush.island.siberia2.api.Reproducible;
import com.javarush.island.siberia2.config.AnimalSettings;
import com.javarush.island.siberia2.entity.Organism;
import com.javarush.island.siberia2.services.EatingService;
import com.javarush.island.siberia2.services.MovementService;
import com.javarush.island.siberia2.services.ReproductionService;
import lombok.Getter;
import lombok.Setter;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public abstract class Animal extends Organism implements Eatable, Reproducible, Movable {
    protected AnimalSettings settings;
    protected double currentFoodLevel;
    protected ReentrantLock lock = new ReentrantLock();
    private boolean hasReproduced = false;
    private boolean hasEaten = false;

    private EatingService eatingService;
    private MovementService movementService;
    private ReproductionService reproductionService;

    public Animal(AnimalSettings settings) {
        this.settings = settings;
        this.weight = settings.getWeight();
        this.currentFoodLevel = settings.getMaxFood();

        //service init
        this.eatingService = new EatingService();
        this.movementService = new MovementService();
        this.reproductionService = new ReproductionService();
    }

    @Override
    public void liveCycle() {
        move();
        eat();
        reproduce();
        decreaseFoodLevel();
    }

    @Override
    public void eat() {
        lock.lock();
        try {
            eatingService.eat(this);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void move() {
        lock.lock();
        try {
            movementService.move(this);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void reproduce() {
        lock.lock();
        try {
            reproductionService.reproduce(this);
        } finally {
            lock.unlock();
        }
    }

    public void die() {
        lock.lock();
        try {
            currentCell.removeAnimal(this);
        } finally {
            lock.unlock();
        }
    }

    public void decreaseFoodLevel() {
        double percentToDecrease = 0.1; // default 0.1 = decrease to 10%
        currentFoodLevel -= settings.getMaxFood() * percentToDecrease;
        if (currentFoodLevel <= 0) {
            die();
        }
    }

    @Override
    public Animal clone() throws CloneNotSupportedException {
        Animal cloned = (Animal) super.clone();
        cloned.lock = new ReentrantLock();
        return cloned;
    }

}