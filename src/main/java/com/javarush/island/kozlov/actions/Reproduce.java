package com.javarush.island.kozlov.actions;

import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.exception.ReproductionException;
import com.javarush.island.kozlov.map.Location;

public interface Reproduce {
    default void reproduce(Location location) throws ReproductionException {
        long countSameSpecies = location.getAnimals().stream()
                .filter(a -> a.getClass().equals(this.getClass()))
                .count();

        if (countSameSpecies >= 2 && location.getAnimals().size() < location.getMaxOnCell()) {
            try {
                location.addAnimal((Animal) this.getClass().getConstructor().newInstance());
                System.out.println("A new " + this.getClass().getSimpleName() + " is born.");
            } catch (Exception e) {
                throw new ReproductionException("Reproduction failed: " + e.getMessage());
            }
        } else {
            System.out.println(this.getClass().getSimpleName() + " could not reproduce: not enough space or animals.");
        }
    }
}
