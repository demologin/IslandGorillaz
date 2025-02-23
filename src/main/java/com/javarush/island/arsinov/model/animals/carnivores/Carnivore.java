package com.javarush.island.arsinov.model.animals.carnivores;

import com.javarush.island.arsinov.model.Cell;
import com.javarush.island.arsinov.model.animals.Animal;
import com.javarush.island.arsinov.model.animals.AnimalAttributes;
import com.javarush.island.arsinov.model.animals.herbivores.Herbivore;

import java.util.List;
import java.util.Random;

public abstract class Carnivore extends Animal {
    private static final Random RANDOM = new Random();

    public Carnivore(String name, int x, int y, AnimalAttributes attributes) {
        super(name, x, y, attributes);
    }

    protected void hunt(Cell cell) {
        List<Animal> preyAnimals = cell.getAnimals();

        for (Animal prey : preyAnimals) {
            if(prey instanceof Herbivore && !isSatiated()){
                eat(prey);
                preyAnimals.remove(prey);
                break;
            }
        }
    }

    protected void reproduce(Cell cell) {
        if (canReproduce()){
            Carnivore offspring = createOffspring();
            cell.getAnimals().add(offspring);
        }
    }

    boolean canReproduce(){
        return isSatiated() && RANDOM.nextDouble() < 0.1;
    }

    protected abstract Carnivore createOffspring();

    @Override
    public void performActions(Cell currentCell, List<Animal> animalsInCell) {
        hunt(currentCell);
        move(currentCell);
        reproduce(currentCell);
    }
}


