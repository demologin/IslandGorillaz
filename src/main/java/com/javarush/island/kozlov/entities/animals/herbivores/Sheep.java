package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Location;

public class Sheep extends Animal implements AnimalsEat {

    public Sheep() {
        super(70, 140, 3, 15);
    }

    @Override
    public void move() {
        System.out.println("Sheep moves");
    }

    @Override
    public void reproduce() {
        System.out.println("Sheep reproduce");
    }

    @Override
    public void eat(Location location) {
        AnimalsEat.super.eat(location, this);
    }
}
