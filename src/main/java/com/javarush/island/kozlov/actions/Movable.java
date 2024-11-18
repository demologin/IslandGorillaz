package com.javarush.island.kozlov.actions;

import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.exception.MoveException;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

import java.util.List;

public interface Movable {
    default void move(Location currentLocation, Island island) throws MoveException {
        List<Location> neighbors = island.getNeighboringLocations(currentLocation);
        for (Location neighbor : neighbors) {
            if (neighbor.getAnimals().size() < neighbor.getMaxOnCell()) {
                synchronized (neighbor) {
                    currentLocation.removeAnimal((Animal) this);
                    neighbor.addAnimal((Animal) this);
                }
                System.out.println(this.getClass().getSimpleName() + " moved to a new location.");
                return;
            }
        }
        System.out.println(this.getClass().getSimpleName() + " could not find a new location.");
    }
}
