package com.javarush.island.siberia.entity.organism.animals.carnivores;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.animals.Animal;

public abstract class Carnivore extends Animal {
    public Carnivore(Location location) {
        super(location);
    }

    //todo может добавить немного общей логики питания, чтобы упростить себе жизнь для каждого хищника потом?

}