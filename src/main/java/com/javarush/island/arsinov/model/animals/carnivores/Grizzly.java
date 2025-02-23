package com.javarush.island.arsinov.model.animals.carnivores;

import com.javarush.island.arsinov.model.Cell;
import com.javarush.island.arsinov.model.animals.Animal;
import com.javarush.island.arsinov.model.animals.AnimalAttributes;
import com.javarush.island.arsinov.model.animals.AnimalParameters;
import com.javarush.island.arsinov.model.animals.AnimalType;

import java.util.List;

public class Grizzly extends Carnivore{

    private static final AnimalAttributes ATTRIBUTES = AnimalParameters.getAttributes(AnimalType.GRIZZLY);

    public Grizzly(int x, int y, AnimalAttributes attributes) {
        super("Grizzly", x, y, attributes);
    }

    @Override
    protected Carnivore createOffspring() {
        return new Grizzly(getX(), getY(), ATTRIBUTES);
    }

    @Override
    public void performActions(Cell currentCell, List<Animal> animalsInCell) {
        hunt(currentCell);
        move(currentCell);
        reproduce(currentCell);
    }
}
