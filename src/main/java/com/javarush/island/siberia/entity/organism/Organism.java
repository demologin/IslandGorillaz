package com.javarush.island.siberia.entity.organism;

import com.javarush.island.siberia.config.OrganismSettings;
import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.animals.Animal;
import com.javarush.island.siberia.utils.RandomUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public abstract class Organism implements Cloneable {
    private UUID id;
    private double weight;
    private Location location;
    private boolean isAlive;
    private OrganismSettings organismSettings;
    private String icon;

    public Organism(Location location) {
        this.id = UUID.randomUUID();
        this.location = location;
        this.isAlive = true;
        this.organismSettings = Settings.getInstance().getOrganismSettings(this.getClass().getSimpleName());
        this.icon = organismSettings.getIcon();
        this.weight = RandomUtils.randomDouble(this.organismSettings.getWeight() / 2, this.organismSettings.getWeight());
    }

    public void die() {
        this.isAlive = false;
        this.location.removeOrganism(this);
    }

    public void grow() {
    }

    public void reproduce() {
        if (canReproduce()) {
            Organism offspring = this.clone();

            if (this.getLocation().canAddOrganism(offspring)) {
                this.getLocation().addOrganism(offspring);
            } else {
                Location targetLocation = findAvailableNeighboringLocation();
                if (targetLocation != null && targetLocation.canAddOrganism(offspring)) {
                    offspring.setLocation(targetLocation);
                    targetLocation.addOrganism(offspring);
                }
            }
        }
    }

    protected boolean canReproduce() {
        if (!this.isAlive()) {
            return false;
        }
        if (this instanceof Animal) {
            Animal animal = (Animal) this;
            double reproductionSatietyThreshold = this.getOrganismSettings().getReproductionSatietyThreshold();
            if (animal.getSatiety() < reproductionSatietyThreshold) {
                return false;
            }
        }
        String species = this.getClass().getSimpleName();
        int maxCountPerCell = this.getOrganismSettings().getMaxCountPerCell();
        int currentCount = this.getLocation().getOrganismCount(species);

        return currentCount < maxCountPerCell;
    }

    private Location findAvailableNeighboringLocation() {
        List<Location> neighboringLocation = this.getLocation().getAdjacentLocations();
        for (Location location : neighboringLocation) {
            if (location.canAddOrganism(this)) {
                return location;
            }
        }
        return null;
    }

    @Override
    public Organism clone() {
        try {
            Organism clone = (Organism) super.clone();
            clone.setId(UUID.randomUUID());

            double minWeight = this.organismSettings.getWeight() / 2;
            double maxWeight = this.organismSettings.getWeight();
            double newWeight = RandomUtils.randomDouble(minWeight, maxWeight);
            clone.setWeight(newWeight);
            clone.setAlive(true);
            clone.setIcon(this.icon);
            clone.setOrganismSettings(this.organismSettings);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("cloning error", e);
        }
    }
}