package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Location;

public class Rabbit extends Animal implements AnimalsEat {

    public Rabbit() {
        super(2, 150, 2, 0.45);

    }

    @Override
    public void move() {
        System.out.println("Rabbit moves");
    }

    @Override
    public void reproduce() {
        System.out.println("Rabbit reproduces");
    }

    @Override
    public void eat(Location location) {
        AnimalsEat.super.eat(location, this);
    }
}
