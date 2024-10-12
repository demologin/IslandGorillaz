package com.javarush.island.kozlov.entities.animals.predators;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Location;

public class Bear extends Animal implements AnimalsEat {

    public Bear() {
        super(500, 5, 2, 80);
    }

    @Override
    public void move() {
        System.out.println("Bear moves");
    }

    @Override
    public void reproduce() {
        System.out.println("Bear reproduces");
    }

    @Override
    public void eat(Location location) {
        AnimalsEat.super.eat(location, this);
    }
}
