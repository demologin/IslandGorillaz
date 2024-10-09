package com.javarush.island.siberia.entity.organism.animals.plants;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.service.ReproductionService;

public class Plant extends Organism {
    private final ReproductionService reproductionService;

    public Plant(Location location) {
        super(location);
        this.reproductionService = new ReproductionService(this);
    }

    @Override
    public void grow() {
        double growRate = this.getOrganismSettings().getGrowthRate();
        this.setWeight(this.getWeight() + growRate);
    }

}