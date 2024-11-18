package com.javarush.island.kozlov.entities.animals.herbivores;


import com.javarush.island.kozlov.actions.Herbivore;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.plants.Vegetation;
import com.javarush.island.kozlov.exception.MoveException;
import com.javarush.island.kozlov.exception.ReproductionException;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Duck extends Animal implements Herbivore {

    public Duck() {
        super(1.0, 200, 4, 0.15);  // Вес, макс. животных на клетке, скорость, потребность в пище
    }

    @Override
    public void eat(Location location) {
        AtomicBoolean hasEaten = new AtomicBoolean(false);

        // Логика поедания растений
        List<Vegetation> plants = location.getPlants();
        if (!plants.isEmpty()) {
            Vegetation plant = plants.remove(0);  // Удаляем растение с клетки
            this.setFoodNeed(Math.max(this.getFoodNeed() - plant.getNutritionalValue(), 0));
            System.out.println("Duck eats vegetation.");
            hasEaten.set(true);
        }

        // Логика поедания животных, которые утка может есть (гусеницы)
        if (this.getFoodNeed() > 0) {
            List<Animal> animals = location.getAnimals();
            animals.stream()
                    .filter(animal -> animal instanceof Worm)  // Используем интерфейс для определения типа пищи
                    .findFirst()
                    .ifPresent(worm -> {
                        animals.remove(worm);  // Убираем гусеницу с клетки
                        this.setFoodNeed(Math.max(this.getFoodNeed() - worm.getWeight(), 0));
                        System.out.println("Duck eats a worm.");
                        hasEaten.set(true);
                    });
        }

        if (!hasEaten.get()) {
            System.out.println("Duck couldn't find anything to eat.");
        } else if (this.getFoodNeed() == 0) {
            System.out.println("Duck is full.");
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
            location.addAnimal(new Rabbit());  // Рождение нового кролика
            this.markAsBorn();
            System.out.println("A new " + this.getClass().getSimpleName() + " is born.");
        } else {
            throw new ReproductionException(this.getClass().getSimpleName() + " couldn't reproduce.");
        }
    }
}
