package com.javarush.island.kozlov.entities.animals.predators;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Location;

public class Fox extends Animal implements AnimalsEat {

    public Fox() {
        super(8, 30, 2, 2);
    }

    @Override
    public void move() {
        System.out.println("Fox moves");
    }

    @Override
    public void reproduce() {
        System.out.println("Fox reproduces");
    }

    @Override
    public void eat(Location location) {
        AnimalsEat.super.eat(location, this);
    }
}
