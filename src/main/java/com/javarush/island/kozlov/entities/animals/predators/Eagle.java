package com.javarush.island.kozlov.entities.animals.predators;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Location;

public class Eagle extends Animal implements AnimalsEat {

    public Eagle() {
        super(6, 20, 3, 1);
    }

    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }

    @Override
    public void eat(Location location) {
        AnimalsEat.super.eat(location, this);
    }
}
