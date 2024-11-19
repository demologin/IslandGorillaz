package com.javarush.island.nikitin.domain.entity.biota.animals.predators;

import com.javarush.island.nikitin.domain.entity.biota.Props;
import com.javarush.island.nikitin.domain.entity.biota.animals.Animal;

public abstract class Predator extends Animal {
    public Predator(Props props) {
        super(props);
    }
}
