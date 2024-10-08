package com.javarush.island.siberia.entity.organism.animals.herbivores;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.animals.carnivores.Carnivore;

public class Horse extends Herbivore {

    public Horse(Location location) {
        super(location);
    }

    @Override
    public void move(){}

}