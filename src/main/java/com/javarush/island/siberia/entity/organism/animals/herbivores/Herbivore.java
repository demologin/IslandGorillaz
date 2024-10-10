package com.javarush.island.siberia.entity.organism.animals.herbivores;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.animals.Animal;

/**
 * The Herbivore class is a subclass of Animal, representing plant-eating animals on the island.
 * Herbivores feed on plants and increase their satiety level based on the amount of food consumed.
 */

public abstract class Herbivore extends Animal {
    public Herbivore(Location location) {
        super(location);
    }

}