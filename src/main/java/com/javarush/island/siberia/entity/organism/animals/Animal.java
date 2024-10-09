package com.javarush.island.siberia.entity.organism.animals;

import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.animals.carnivores.Carnivore;
import com.javarush.island.siberia.entity.organism.animals.herbivores.Herbivore;
import com.javarush.island.siberia.entity.organism.animals.plants.Plant;
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
        boolean hasFood = this.hasFoodInLocation();
        boolean hasMate = this.hasMateInLocation();

        if (!hasFood && !hasMate) {
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

    private boolean hasFoodInLocation() {
        List<Organism> organisms = this.getLocation().getOrganisms();

        if (this instanceof Carnivore carnivore) {
            for (Organism organism : organisms) {
                if (organism != this && carnivore.isPrey(organism)) {
                    return true;
                }
            }
        }

        if (this instanceof Herbivore) {
            for (Organism organism : organisms) {
                if (organism instanceof Plant) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasMateInLocation() {
        List<Organism> organisms = this.getLocation().getOrganisms();
        long sameSpeciesCount = organisms.stream()
                .filter(o -> o.getClass().equals(this.getClass()))
                .count();
        return sameSpeciesCount > 1;
    }

}