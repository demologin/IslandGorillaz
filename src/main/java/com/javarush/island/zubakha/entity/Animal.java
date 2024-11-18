package com.javarush.island.zubakha.entity;


import com.javarush.island.zubakha.entity.map.Cell;
import com.javarush.island.zubakha.entity.map.GameMap;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public abstract class Animal extends LifeForm {
    private final int maxSpeed;
    private final double maxFood;
    @Setter
    private double health;


    public Animal(double weight, int speed, double maxFood, int maxCountInCell, String name) {
        super(weight, maxCountInCell, name);
        this.maxSpeed = speed;
        this.maxFood = maxFood;
        this.health = maxFood; // На старте максимальное количество здоровья
    }


       public boolean eat(Object food) {
        double chanceToEat;
        LifeForm lifeForm;
        boolean animalEatFood;

        if (food instanceof LifeForm) {
            lifeForm = (LifeForm) food;
        } else {
            try {
                throw new Exception("The object is not an animal/plant.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        String foodName = lifeForm.getName();
        chanceToEat = getChanceToEat(foodName);

        animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;
        if (animalEatFood) {
            setHealth(Math.min((getHealth() + lifeForm.getWeight()), getMaxFood()));
            Cell cell = GameMap.
                    getInstance().
                    getLocation(lifeForm.getRow(), lifeForm.getCol());
            if (lifeForm instanceof Animal animal) {
                if (cell.getAnimals().contains(animal)) {
                    GameMap.
                            getInstance().
                            removeAnimal(animal, cell.getRow(), cell.getCol());
                } else {
                    return false;
                }
            } else {
                Plant plant = (Plant) lifeForm;
                if (!cell.getPlants().isEmpty()) {
                    GameMap.getInstance().removePlant(plant, cell.getRow(), cell.getCol());
                } else {
                    return false;
                }
            }
        }
        return animalEatFood;
    }


    public abstract double getChanceToEat(String foodName);


    public abstract void multiply(Animal partner);


    public void move() {
        Random random = new Random();
        int randomCells = random.nextInt(getMaxSpeed()) + 1;
        int direction = random.nextInt(4);
        int newRow = getRow();
        int newCol = getCol();
        switch (direction) {
            case 0 ->
                    newRow -= randomCells;
            case 1 ->
                    newRow += randomCells;
            case 2 ->
                    newCol -= randomCells;
            case 3 ->
                    newCol += randomCells;
        }
        while (newRow < 0 || newRow >= GameMap.getInstance().getRow() || newCol < 0 || newCol >= GameMap.getInstance().getCol()) {
            randomCells = random.nextInt(getMaxSpeed()) + 1;
            direction = random.nextInt(4);
            newRow = getRow();
            newCol = getCol();
            switch (direction) {
                case 0 ->
                        newRow -= randomCells;
                case 1 ->
                        newRow += randomCells;
                case 2 ->
                        newCol -= randomCells;
                case 3 ->
                        newCol += randomCells;
            }
        }
        GameMap.getInstance().removeAnimal(this, getRow(), getCol());
        setRow(newRow);
        setCol(newCol);
        GameMap.getInstance().addAnimal(this, newRow, newCol);
    }


}
