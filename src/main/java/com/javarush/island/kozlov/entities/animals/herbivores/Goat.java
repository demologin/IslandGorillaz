package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Location;

public class Goat extends Animal implements AnimalsEat {

    public Goat() {
        super(60, 140, 3, 10);
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
