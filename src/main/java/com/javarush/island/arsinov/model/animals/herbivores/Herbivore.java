package com.javarush.island.arsinov.model.animals.herbivores;

import com.javarush.island.arsinov.model.Cell;
import com.javarush.island.arsinov.model.animals.Animal;
import com.javarush.island.arsinov.model.animals.AnimalAttributes;

import java.util.Random;

public abstract class Herbivore extends Animal {

    private static final Random RANDOM = new Random();

    public Herbivore(String name, int x, int y, AnimalAttributes attributes) {
        super(name, x, y, attributes);
    }

    protected void graze(Cell cell) {
        double plantMass = cell.getPlantMass();
        double foodRequired = getFoodNeed();

        if (plantMass > 0) {
            double eaten = Math.min(plantMass, foodRequired);
            cell.decreasePlantMass(eaten);
            increaseSatiety(eaten);
        }
    }

    protected void reproduce(Cell cell) {
        if (canReproduce()) {
            Herbivore offspring = createOffspring();
            cell.getAnimals().add(offspring);
        }
    }

    protected boolean canReproduce() {
        return isSatiated() && Math.random() < 0.5;
    }

    protected abstract Herbivore createOffspring();

    private int randomStep() {
        return RANDOM.nextInt(getSpeed() + 1);
    }

    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    @Override
    public void checkHealth(Cell cell) {
        if (this.getHeight() <= 0){
            dia();
        }

        if (cell.getPlantMass() <= 0){
            dia();
        } else {
            consumeFood(cell);
        }
    }

    private void consumeFood(Cell cell) {
        double foodNeed = getFoodNeed();
        if(cell.getPlantMass() >= foodNeed){
            cell.decreasePlantMass(foodNeed);
        }
    }
}
