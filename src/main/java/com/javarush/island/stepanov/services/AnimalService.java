package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Movable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AnimalService extends Organism implements Movable, Reproduceble, Eatable,Cloneable {
    protected int maxSpeed;
    protected double maxFood;
    @Setter
    protected double weight;
    @Override
    public void eat(Cell cell) {
        Map<String, Integer> foodMap = Setting.get().getFoodMap(name);
        double weightOfFoodEaten = 0;
        HashMap<String, List<Organism>> residentMap = cell.getResidentMap();

        for (Map.Entry<String, List<Organism>> entry : residentMap.entrySet()) {
            String name = entry.getKey();
            List<Organism> organismList = entry.getValue();

            if (foodMap.containsKey(name)) {
                Iterator<Organism> iterator = organismList.iterator(); // Используем итератор
                while (iterator.hasNext()) {
                    Organism organism = iterator.next();
                    if (eatOrganism(organism, weightOfFoodEaten)) {
                        iterator.remove(); // Безопасное удаление элемента
                        System.out.println(name + " eat " + organism.getName());
                    }
                }
            }
        }
    }

    private boolean eatOrganism(Organism food, double weightOfFoodEaten) {
        double weightCanEat = (maxFood - weightOfFoodEaten) * flockSize;
        int foodFlockSize = food.getFlockSize();
        double weightOfFood = food.getWeight() * foodFlockSize;

        if (weightOfFood < weightCanEat) {
            weightOfFoodEaten += weightOfFood;
            return true; // Организм съеден
        } else {
            weightOfFoodEaten = maxFood;
            return false; // Организм не съеден
        }
    }



    @Override
    public double getWeight() {
        return weight;
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
