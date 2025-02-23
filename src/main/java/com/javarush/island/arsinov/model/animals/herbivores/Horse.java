package com.javarush.island.arsinov.model.animals.herbivores;


import com.javarush.island.arsinov.model.Cell;
import com.javarush.island.arsinov.model.animals.Animal;
import com.javarush.island.arsinov.model.animals.AnimalAttributes;
import com.javarush.island.arsinov.model.animals.AnimalParameters;
import com.javarush.island.arsinov.model.animals.AnimalType;

import java.util.List;

public class Horse extends Herbivore {

    private static final AnimalAttributes ATTRIBUTES = AnimalParameters.getAttributes(AnimalType.HORSE);

    public Horse(int x, int y, AnimalAttributes attributes) {
        super("Horse", x, y, attributes);
    }

    @Override
    protected Herbivore createOffspring() {
        return new Horse(getX(), getY(), ATTRIBUTES);
    }

    @Override
    public void performActions(Cell currentCell, List<Animal> animalsInCell) {
        graze(currentCell);
        move(currentCell);
        reproduce(currentCell);
    }
}
