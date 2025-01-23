package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Movable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;
import lombok.Setter;

import java.util.*;

public class AnimalService extends Organism implements Movable, Reproduceble, Eatable,Cloneable {
    protected int maxSpeed;
    protected double maxFood;
    @Override
    public boolean eat(Cell cell) {
        Map<String, Integer> foodMap = Setting.get().getFoodMap(name);
        double weightOfFoodEaten = 0;
        HashMap<String, List<Organism>> residentMap = cell.getResidentMap();

        for (Map.Entry<String, List<Organism>> entry : residentMap.entrySet()) {
            String foodName = entry.getKey();
            List<Organism> organismList = entry.getValue();

            if (foodMap.containsKey(foodName)) {
                Iterator<Organism> iterator = organismList.iterator();
                while (iterator.hasNext()) {
                    Organism organism = iterator.next();
                    if (eatOrganism(organism, weightOfFoodEaten)) {
                        iterator.remove(); // Безопасное удаление элемента
                        System.out.println(name + " eat " + organism.getName());
                    }
                    if (isNotHungry(weightOfFoodEaten)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isNotHungry(double weightOfFoodEaten) {
        return (weightOfFoodEaten == maxFood) || (weight >= maxWeight);
    }

    private boolean eatOrganism(Organism food, double weightOfFoodEaten) {
        double weightCanEat = (maxFood - weightOfFoodEaten) * flockSize;
        int foodFlockSize = food.getFlockSize();
        double weightOfFood = food.getWeight();
        double weightOfFoodFlock = weightOfFood * foodFlockSize;
        if (weightOfFoodFlock < weightCanEat) {
            weightOfFoodEaten += weightOfFoodFlock;
            weight+=weightOfFoodFlock;
            return true;
        } else {
            weightOfFoodEaten = maxFood;
            double newWeightOfFoodFlock = weightOfFoodFlock-weightCanEat;
            double newWeightOfFood = newWeightOfFoodFlock/foodFlockSize;
            food.setWeight(newWeightOfFood);
            return false;
        }
    }

    @Override
    public void move() {
//        System.out.println(name + " is moving");
    }

    @Override
    public void reproduce() {
//        System.out.println(name + " is reproducing");
    }

    @Override
    public AnimalService clone() {
        return (AnimalService) super.clone();
    }
}
