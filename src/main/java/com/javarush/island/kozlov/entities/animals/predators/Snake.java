package com.javarush.island.kozlov.entities.animals.predators;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Location;

public class Snake extends Animal implements AnimalsEat {

    public Snake() {
        super(15, 30, 1, 3);
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
