package com.javarush.island.siberia.entity.organism.animals.herbivores;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.animals.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(Location location) {
        super(location);
    }

}