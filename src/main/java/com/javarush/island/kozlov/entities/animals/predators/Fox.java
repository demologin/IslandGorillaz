package com.javarush.island.kozlov.entities.animals.predators;


import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Herbivore;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.exception.MoveException;
import com.javarush.island.kozlov.exception.ReproductionException;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

import java.util.List;


public class Fox extends Animal{


    public Fox() {
        super(8, 30, 2, 2);
    }


    @Override
    public void eat(Location location) {
        // Логика поедания другими животными
        if (this.getFoodNeed() > 0) {
            for (Animal prey : location.getAnimals()) {
                if (prey instanceof Herbivore) {  // Проверяем, является ли животное травоядным
                    location.removeAnimal(prey);  // Убираем съеденное животное с клетки
                    this.setFoodNeed(Math.max(this.getFoodNeed() - prey.getWeight(), 0));
                    System.out.println(this.getClass().getSimpleName() + " eats " + prey.getClass().getSimpleName());
                    this.resetHungerCycles();
                    return;  // Животное наелось
                }
            }
            // Если травоядных нет
            System.out.println(this.getClass().getSimpleName() + " couldn't find any prey.");
            this.increaseHungerCycles();
        }
    }

    @Override
    public void move(Location currentLocation, Island island) throws MoveException {
        List<Location> neighbors = island.getNeighboringLocations(currentLocation);
        for (Location neighbor : neighbors) {
            if (neighbor.getAnimals().size() < neighbor.getMaxOnCell()) {
                synchronized (neighbor) {
                    currentLocation.removeAnimal(this);
                    neighbor.addAnimal(this);
                }
                System.out.println(this.getClass().getSimpleName() + " moved to a new location.");
                return;
            }
        }
        throw new MoveException(this.getClass().getSimpleName() + " could not move.");
    }

    @Override
    public void reproduce(Location location) throws ReproductionException {
        long countSameSpecies = location.getAnimals().stream()
                .filter(a -> a.getClass().equals(this.getClass()))
                .count();

        if (countSameSpecies >= 2) {
            location.addAnimal(new Wolf());  // Рождение нового волка
            this.markAsBorn();
            System.out.println("A new " + this.getClass().getSimpleName() + " is born.");
        } else {
            throw new ReproductionException(this.getClass().getSimpleName() + " couldn't reproduce.");
        }
    }
}
