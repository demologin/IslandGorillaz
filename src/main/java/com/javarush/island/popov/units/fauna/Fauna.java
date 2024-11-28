package com.javarush.island.popov.units.fauna;

import com.javarush.island.popov.interfaces.units.Eatable;
import com.javarush.island.popov.interfaces.units.Moveable;
import com.javarush.island.popov.interfaces.units.Reproduceable;
import com.javarush.island.popov.map.Cell;
import com.javarush.island.popov.repo.Constants;
import com.javarush.island.popov.units.Unit;
import com.javarush.island.popov.utilits.Randomizer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public abstract class Fauna extends Unit implements Eatable, Moveable, Reproduceable {
    public Fauna(String name, String icon, double maxUnitWeight, int maxUnitsInCell, int maxUnitSpeedPerStep, double maxFoodForSaturation, int percentProbably) {
        super(name, icon, maxUnitWeight, maxUnitsInCell, maxUnitSpeedPerStep, maxFoodForSaturation, percentProbably);
    }

    public Fauna() {
        super();
    }

    @Override
    public void eat(Cell cell) {
        cell.getLock().lock();
        try {
            if (cell.checkUnitInCell(this)) {
                Map<Class<? extends Unit>, CopyOnWriteArrayList<Unit>> realFoodsForThis = findRealFoodsForThis(cell);
                if (!realFoodsForThis.isEmpty()) {
                    List<String> realFoodsNamesForThis = realFoodsForThis.entrySet().stream().map(Map.Entry::getKey).map(Class::getSimpleName).toList();
                    Map<String, Integer> chancesToEatFood = getMenuForAnimals();
                    double needToEat = wantsToEat();
                    int listIndex = Randomizer.rnd(0, realFoodsNamesForThis.size());
                    String nameFood = realFoodsNamesForThis.get(listIndex);
                    Integer chanceToEatFood = chancesToEatFood.get(nameFood);
                    boolean willBreakfestHappens = Randomizer.getRandom(chanceToEatFood);
                    double weightThis = getWeight();
                    if (willBreakfestHappens) {
                        CopyOnWriteArrayList<Unit> listFood = realFoodsForThis.entrySet().stream().filter(entry -> entry.getKey()
                                .getSimpleName().equals(nameFood)).map(Map.Entry::getValue).toList().getFirst();
                        Iterator<Unit> iterator = listFood.iterator();
                        while (iterator.hasNext() && !listFood.isEmpty()){
                            Unit food = iterator.next();
                            double foodWeight = food.getWeight();
                            if (foodWeight <= needToEat) {
                                setWeight(weightThis + foodWeight);
                                listFood.remove(food);
                                needToEat-=foodWeight;
                            } else {
                                setWeight(weightThis + needToEat);
                                food.setWeight(foodWeight-needToEat);
                                needToEat=0;
                            }
                        }

                    } else {
                        changeWeightBecauseHungry(cell, weightThis);
                    }
                }
            }
        }finally {
            cell.getLock().unlock();
        }
    }

    private void changeWeightBecauseHungry(Cell cell, double weightThis) {
        weightThis -= getMaxUnitWeight()* Constants.PERSENT_LOOSE_WEIGHT /100;
        if (weightThis <=0) {
            cell.removeUnit(this);
        }
        setWeight(weightThis);
    }

    public Map<Class<? extends Unit>, CopyOnWriteArrayList<Unit>> findRealFoodsForThis(Cell cell) {
                Map<String, Integer> potentionMenuForThis = getMenuForAnimals();
                List<String> namesPotentialFoodForThis = potentionMenuForThis.entrySet().stream()
                        .filter(entry -> entry.getValue() > 0)
                        .map(Map.Entry::getKey).toList();

                Map<Class<? extends Unit>, CopyOnWriteArrayList<Unit>> allUnitsInCell = cell.getAllUnitsInCell();

        Map<Class<? extends Unit>, CopyOnWriteArrayList<Unit>> realFoodForThis = allUnitsInCell.entrySet().stream().filter(entry -> {
                    String foodName = entry.getKey().getSimpleName();
                    return namesPotentialFoodForThis.contains(foodName);
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return realFoodForThis;
    }

    @Override
    public void move(Cell currentCell) {
        int countSteps = getMaxUnitSpeedPerStep();
        Cell nextCell = currentCell.findNextCell(countSteps);
        try {
            currentCell.getLock().lock();
            try {
                nextCell.getLock().lock();
                if (currentCell.checkUnitInCell(this) && nextCell.haveEnoughtSpace(this)) {
                    nextCell.addUnit(this);
                    currentCell.removeUnit(this);
                }

            } finally {
                nextCell.getLock().unlock();
            }
        } finally {
            currentCell.getLock().unlock();
        }
    }

    @Override
    public void reproduce(Cell cell) {
        cell.getLock().lock();
        try {
            CopyOnWriteArrayList<Unit> unitsList = cell.getAllUnitsInCell().get(getType());
            if (cell.checkUnitInCell(this) && cell.haveEnoughtSpace(this) && this.getWeight() > this.getMaxUnitWeight() / 3) {
                int countsClone = Randomizer.rnd(0, getMaxUnitsInCell() - unitsList.size());
                for (int i = 0; i < countsClone; i++) {
                    Unit child = Unit.clone(this);
                    child.setWeight(this.getWeight() / 3);
                    unitsList.add(child);
                    double weightParent = this.getWeight();
                    double weightParentAfterBirth = weightParent - child.getWeight();
                    this.setWeight(weightParentAfterBirth);
                }
            }
        } finally {
            cell.getLock().unlock();
        }
    }

}
