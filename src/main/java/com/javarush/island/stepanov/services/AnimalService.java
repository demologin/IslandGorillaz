package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Movable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;
import com.javarush.island.stepanov.util.Rnd;

import java.util.*;

public class AnimalService extends Organism implements Movable, Reproduceble, Eatable,Cloneable {
    protected int maxSpeed;
    protected double maxFood;
    @Override
    public boolean eat(Cell cell) {
        if (weight>=maxWeight) {
            return true;
        }

        double weightOfFoodEaten = 0;
        Map<String, Integer> foodMap = Setting.get().getFoodMap(name);
        HashMap<String, List<Organism>> residentMap = cell.getResidentMap();

        for (Map.Entry<String, List<Organism>> entry : residentMap.entrySet()) {
            String foodName = entry.getKey();
            List<Organism> organismList = entry.getValue();

            if (foodMap.containsKey(foodName)) {
                Iterator<Organism> iterator = organismList.iterator();
                int probabilityOfBeingEaten = foodMap.get(foodName);
                while (iterator.hasNext()) {
                    Organism organism = iterator.next();
                    if (eatOrganism(organism,probabilityOfBeingEaten, weightOfFoodEaten)) {
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

    @Override
    public void reproduce(Cell cell) {
        if (weight>=maxWeight){
            List<Organism> list = cell.getResidentMap().get(name);
            double birthWeightLossRate = Setting.get().getBirthWeightLossRate();
            double newWeight = weight*birthWeightLossRate;
            setWeight(newWeight);
            AnimalService newanimal = clone();
            list.add(newanimal);
        }
    }

    private boolean isNotHungry(double weightOfFoodEaten) {
        return (weightOfFoodEaten == maxFood) || (weight >= maxWeight);
    }

    private boolean eatOrganism(Organism food,int probabilityOfBeingEaten, double weightOfFoodEaten) {
        if (!Rnd.getForPercent(probabilityOfBeingEaten)){
            System.out.println(name+" не смог съесть "+food.getName());
            return false;
        }
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
    public AnimalService clone() {
        return (AnimalService) super.clone();
    }
}
