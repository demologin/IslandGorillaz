package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Location;

public class Horse extends Animal implements AnimalsEat {

    public Horse() {
        super(400, 20, 4, 60);
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
