package com.javarush.island.siberia.entity.organism.animals.carnivores;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.animals.Animal;
import com.javarush.island.siberia.service.Probability;

/**
 * The Carnivore class is a subclass of Animal, representing meat-eating animals on the island.
 * Carnivores hunt other animals based on specific prey probabilities defined in the settings.
 */

public abstract class Carnivore extends Animal {
    public Carnivore(Location location) {
        super(location);
    }

    /**
     * The eat method allows the carnivore to hunt and eat other animals in the current location.
     * The prey is determined based on the probabilities from the settings.
     */

    public boolean isPrey(Organism organism) {
        String preySpecies = organism.getClass().getSimpleName();
        int probability = Probability.getProbability(this.getClass().getSimpleName(), preySpecies);
        return probability > 0;
    }

}