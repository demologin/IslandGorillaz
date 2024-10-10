package com.javarush.island.siberia.entity.organism;

import com.javarush.island.siberia.config.OrganismSettings;
import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.service.ReproductionService;
import com.javarush.island.siberia.utils.RandomUtils;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * The abstract class Organism represents any living entity on the island,
 * including plants and animals. It provides common functionality for
 * managing organism state, such as location, weight, and actions like
 * reproduction and death.
 */

@Getter
@Setter
public abstract class Organism implements Cloneable {
    private UUID id;
    private double weight;
    private Location location;
    private boolean isAlive;
    private OrganismSettings organismSettings;
    private String icon;
    private final ReproductionService reproductionService;

    /**
     * Constructs a new organism in a given location.
     * Initializes the organism's settings from the configuration and assigns
     * a random initial weight.
     *
     * @param location The location where the organism starts.
     */

    public Organism(Location location) {
        this.id = UUID.randomUUID();
        this.location = location;
        this.isAlive = true;
        this.organismSettings = Settings.getInstance().getOrganismSettings(this.getClass().getSimpleName());
        this.icon = organismSettings.getIcon();
        this.weight = RandomUtils.randomDouble(this.organismSettings.getWeight() / 2, this.organismSettings.getWeight());
        this.reproductionService = new ReproductionService(this);
    }

    public void die() {
        this.isAlive = false;
        this.location.removeOrganism(this);
    }

    /**
     * Increases the weight of the organism according to its growth rate.
     * This method is primarily used for plants to represent growth.
     */

    public void grow() {
    }

    /**
     * Handles the reproduction of the organism.
     * If reproduction is possible, a new organism (offspring) is created and
     * placed either in the current or an adjacent location.
     */

    public void reproduce() {
        reproductionService.reproduce();
    }

    /**
     * Clones the organism, creating a new instance with a random weight and a unique ID.
     *
     * @return A new Organism object cloned from this instance.
     */

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