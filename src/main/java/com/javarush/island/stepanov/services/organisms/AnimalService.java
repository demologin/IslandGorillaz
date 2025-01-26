package com.javarush.island.stepanov.services.organisms;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.oganism.interfaces.Movable;
import com.javarush.island.stepanov.util.Rnd;

import java.util.*;

import static com.javarush.island.stepanov.constants.Constants.*;

public class AnimalService extends OrganismService implements Movable{
    protected int maxSpeed;
    protected double maxFood;

    @Override
    public boolean eat(Cell cell) {
        if (weight>=maxWeight) {
            return true;
        }
        double weightOfFoodEaten = START_WEIGHT_OF_FOOD_EATEN;
        Set<String> organismsSet = cell.getOrganismsSet();
        Map<String, Integer> foodMap = Setting.get().getFoodMap(name);
        for (String foodName : organismsSet) {
            if (foodMap.containsKey(foodName)) {
                List<Organism> organismList = cell.getOrganisms(foodName);
                Iterator<Organism> iterator = organismList.iterator();
                int probabilityOfBeingEaten = foodMap.get(foodName);
                while (iterator.hasNext()) {
                    Organism organism = iterator.next();
                    if (eatOrganism(organism,probabilityOfBeingEaten, weightOfFoodEaten)) {
                        iterator.remove();
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

    private boolean eatOrganism(Organism food, int probabilityOfBeingEaten, double weightOfFoodEaten) {
        if (!Rnd.getForPercent(probabilityOfBeingEaten)){
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
            weight+=weightCanEat;
            double newWeightOfFoodFlock = weightOfFoodFlock-weightCanEat;
            double newWeightOfFood = newWeightOfFoodFlock/foodFlockSize;
            food.setWeight(newWeightOfFood);
            return false;
        }
    }

    @Override
    public void move(Cell currentCell) {
        Cell cellToMove = getCellToMove(currentCell);
        if(!cellToMove.equals(currentCell)) {
            currentCell.removeOrganism(this);
            cellToMove.addOrganism(this);
        }
    }

    private Cell getCellToMove(Cell currentCell) {
        int countOfMoves = Rnd.random(MIN_SPEED,maxSpeed);
        if (countOfMoves!=0){
            for (int i = 0; i < countOfMoves; i++) {
                int randomNumberOfNextCell = Rnd.random(FIRST_NUMBRER,MAX_CELL_NUMBER);
                List<Cell> nextCellList = currentCell.getNextCell();
                currentCell = nextCellList.get(randomNumberOfNextCell);
            }
        }
        return currentCell;
    }

    @Override
    public AnimalService clone() {
        return (AnimalService) super.clone();
    }
}
