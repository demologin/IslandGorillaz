package com.javarush.island.kozlov.entities.animals;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.logic.ProbabilityTable;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public abstract class Animal implements AnimalsEat, Movable {

    public double weight;
    protected int maxOnCell;
    protected int speed;
    public double foodNeed;

    public Animal (double weight, int maxOnCell, int speed, double foodNeed) {

        this.weight = weight;
        this.maxOnCell = maxOnCell;
        this.speed = speed;
        this.foodNeed = foodNeed;
    }

    // Метод для передвижения
    public void move(Location currentLocation, Island island) {
        Location newLocation = island.getRandomNeighboringLocation(currentLocation);

        if (newLocation != null) {
            synchronized (newLocation) {
                synchronized (currentLocation) {
                    currentLocation.removeAnimal(this);
                    newLocation.addAnimal(this);
                    System.out.println("Animal moves to a new location");
                }
            }
        }
    }

    // Метод для размножения
    public void reproduce(Location location) {

    }


    // Метод для питания
    @Override
    public void eat(Location location, Animal predator) {
        AnimalsEat.super.eat(location, predator);
    }

}
