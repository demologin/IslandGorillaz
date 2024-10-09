package com.javarush.island.siberia.entity.organism.animals.carnivores;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.animals.Animal;
import com.javarush.island.siberia.service.Probability;

public abstract class Carnivore extends Animal {
    public Carnivore(Location location) {
        super(location);
    }

    public boolean isPrey(Organism organism) {
        String preySpecies = organism.getClass().getSimpleName();
        int probability = Probability.getProbability(this.getClass().getSimpleName(), preySpecies);
        return probability > 0;
    }

}