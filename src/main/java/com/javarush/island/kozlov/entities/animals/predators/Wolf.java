package com.javarush.island.kozlov.entities.animals.predators;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Location;

public class Wolf extends Animal implements AnimalsEat {

    public Wolf() {
        super(50, 20, 5, 7);
    }

    @Override
    public void move() {
        System.out.println("Wolf moves");
    }

    @Override
    public void reproduce() {
        System.out.println("Wolf reproduces");
    }

    @Override
    public void eat(Location location) {
        AnimalsEat.super.eat(location, this);
    }
}
