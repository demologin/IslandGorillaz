package com.javarush.island.arsinov.model.animals.herbivores;

import com.javarush.island.arsinov.model.Cell;
import com.javarush.island.arsinov.model.animals.Animal;
import com.javarush.island.arsinov.model.animals.AnimalAttributes;
import com.javarush.island.arsinov.model.animals.AnimalParameters;
import com.javarush.island.arsinov.model.animals.AnimalType;

import java.util.List;

public class Duck extends Herbivore{

    private static final AnimalAttributes ATTRIBUTES = AnimalParameters.getAttributes(AnimalType.DUCK);

    public Duck(int x, int y, AnimalAttributes attributes) {
        super("Duck", x, y, attributes);
    }

    @Override
    protected Herbivore createOffspring() {
        return null;
    }

    @Override
    public void performActions(Cell currentCell, List<Animal> animalsInCell) {

    }
}
