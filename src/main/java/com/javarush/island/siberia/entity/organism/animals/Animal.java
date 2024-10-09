package com.javarush.island.siberia.entity.organism.animals;

import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.service.EatingBehavior;
import com.javarush.island.siberia.utils.RandomUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class Animal extends Organism implements Hunger{
    private double maxFood;
    private double currentFood;
    private int speed;
    private double satiety;
    private double hunger;
    private final EatingBehavior eatingBehavior;

    public Animal(Location location) {
        super(location);
        this.maxFood = this.getOrganismSettings().getMaxFood();
        this.speed = this.getOrganismSettings().getSpeed();
        this.currentFood = RandomUtils.randomDouble(0, this.maxFood);
        this.satiety = this.currentFood / this.maxFood;
        this.eatingBehavior = new EatingBehavior();
    }

    public void eat() {
        eatingBehavior.eat(this);
    }

    public void move() {
        List<Location> adjacentLocations = this.getLocation().getAdjacentLocations();
        int speed = this.getOrganismSettings().getSpeed();
        int steps = RandomUtils.randomInt(1, speed);

        for (int i = 0; i < steps; i++) {
            if (adjacentLocations.isEmpty()) {
                break;
            }

            Location targetLocation = adjacentLocations.get(RandomUtils.randomInt(0, adjacentLocations.size() - 1));

            if (targetLocation.canAddOrganism(this)) {
                this.getLocation().removeOrganism(this);
                targetLocation.addOrganism(this);
                this.setLocation(targetLocation);
                break;
            }
        }
    }

    @Override
    public void increaseHunger() {
        double hungerIncreaseRate = Settings.getInstance().getHungerIncreaseRate();
        this.hunger += hungerIncreaseRate;
        if (this.isStarving()) {
            this.die();
        }
    }

    @Override
    public boolean isStarving() {
        return this.hunger >= 1.0;
    }

    public void resetHunger() {
        this.hunger = 0;
    }

}