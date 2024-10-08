package com.javarush.island.siberia.entity.organism.animals.plants;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;

public class Plant extends Organism {
    public Plant(Location location) {
        super(location);
    }

    @Override
    public void grow() {
        double growRate = this.getOrganismSettings().getGrowthRate();
        this.setWeight(this.getWeight() + growRate);
    }

    @Override
    public void reproduce() {}

}