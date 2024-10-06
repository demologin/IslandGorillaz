package com.javarush.island.siberia.entity.organism;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class Organism implements Cloneable{
    private UUID id;
    private double weight;
    private Location location;
    private boolean isAlive;

    public Organism(double weight, Location location) {
        this.id = UUID.randomUUID();
        this.weight = weight;
        this.location = location;
        this.isAlive = true;
    }

    public void die() {
        this.isAlive = false;
        this.location.removeOrganism(this);
    }

    public void grow() {}

    public void reproduce() {
        if (canReproduce()) {
            Organism offspring = this.clone();
            offspring.setLocation(this.location);
            this.location.addOrganizm(offspring);
        }
    }

    protected boolean canReproduce() {
        return true;
    }

    @Override
    public Organism clone() {
        try {
            Organism clone = (Organism) super.clone();
            clone.setId(UUID.randomUUID());

            double minWeight = this.weight / 2;
            double maxWeight = this.weight;
            double newWeight = RandomUtils.randomDouble(minWeight, maxWeight);
            clone.setWeight(newWeight);
            clone.setAlive(true);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning is not support for class " + this.getClass().getSimpleName(), e);
        }
    }
}