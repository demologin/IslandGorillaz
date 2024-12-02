package com.javarush.island.arsinov.model.animals.herbivores;

import com.javarush.island.arsinov.model.Cell;
import com.javarush.island.arsinov.model.animals.Animal;
import com.javarush.island.arsinov.model.animals.AnimalAttributes;
import com.javarush.island.arsinov.model.animals.AnimalParameters;
import com.javarush.island.arsinov.model.animals.AnimalType;

import java.util.List;

public class Sheep extends Herbivore{

    private static final AnimalAttributes ATTRIBUTES = AnimalParameters.getAttributes(AnimalType.SHEEP);

    public Sheep(int x, int y, AnimalAttributes attributes) {
        super("SHEEP", x, y, attributes);
    }

    @Override
    protected Herbivore createOffspring() {
        return new Sheep(getX(), getY(), ATTRIBUTES);
    }

    @Override
    public void performActions(Cell currentCell, List<Animal> animalsInCell) {
        graze(currentCell);
        move(currentCell);
        reproduce(currentCell);
    }
}
