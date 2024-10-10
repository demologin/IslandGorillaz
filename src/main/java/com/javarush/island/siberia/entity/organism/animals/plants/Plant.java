package com.javarush.island.siberia.entity.organism.animals.plants;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.service.ReproductionService;

/**
 * The Plant class represents a plant organism on the island.
 * Plants grow over time by increasing their weight and can reproduce to spread across multiple locations.
 */

public class Plant extends Organism {
    private final ReproductionService reproductionService;

    public Plant(Location location) {
        super(location);
        this.reproductionService = new ReproductionService(this);
    }

    /**
     * Increases the plant's weight based on its growth rate.
     * The growth rate is defined in the organism settings and represents how much the plant grows each simulation cycle.
     */

    @Override
    public void grow() {
        double growRate = this.getOrganismSettings().getGrowthRate();
        this.setWeight(this.getWeight() + growRate);
    }

}