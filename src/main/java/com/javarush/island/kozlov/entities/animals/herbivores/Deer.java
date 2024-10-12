package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Location;

public class Deer extends Animal implements AnimalsEat {

    public Deer() {
        super(300, 20, 4, 50);
    }

    @Override
    public void eat(Location location) {
        AnimalsEat.super.eat(location, this);
    }

    @Override
    public void reproduce() {
        System.out.println("Deer reproduce");
    }

    @Override
    public void move() {
        System.out.println("Deer moves");
    }
}
