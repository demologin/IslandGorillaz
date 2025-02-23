package com.javarush.island.arsinov.model.animals.herbivores;

import com.javarush.island.arsinov.model.Cell;
import com.javarush.island.arsinov.model.animals.Animal;
import com.javarush.island.arsinov.model.animals.AnimalAttributes;
import com.javarush.island.arsinov.model.animals.AnimalParameters;
import com.javarush.island.arsinov.model.animals.AnimalType;

import java.util.List;

public class Buffalo extends Herbivore {

    private static final AnimalAttributes ATTRIBUTES = AnimalParameters.getAttributes(AnimalType.BUFFALO);

    public Buffalo(int x, int y, AnimalAttributes attributes) {
        super("Buffalo", x, y, attributes);
    }

    @Override
    protected Herbivore createOffspring() {
        return new Buffalo(getX(), getY(), ATTRIBUTES);
    }

    @Override
    public void performActions(Cell currentCell, List<Animal> animalsInCell) {
        graze(currentCell);
        move(currentCell);
        reproduce(currentCell);

    }
}
