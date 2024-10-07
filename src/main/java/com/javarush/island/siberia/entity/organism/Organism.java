package com.javarush.island.siberia.entity.organism;

import com.javarush.island.siberia.config.OrganismSettings;
import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.utils.RandomUtils;
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

    public void grow() {}

    public void reproduce() {
        if (canReproduce()) {
            Organism offspring = this.clone();
            //todo проверить макс организмов в локе
            offspring.setLocation(this.location);
            this.location.addOrganism(offspring);
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