package com.javarush.island.arsinov.model.animals;

import com.javarush.island.arsinov.model.Cell;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.lang.Math.clamp;

public abstract class Animal {
    private final static Random RANDOM = new Random();
    private final String name;
    @Getter
    private final int maxInCell;
    @Getter
    private final int speed;
    @Getter
    protected final double foodNeed;
    @Getter
    private final AnimalAttributes attributes;

    private double weight;
    @Getter
    private double height;
    private double satiety;
    @Getter
    private int x, y;
    private boolean isAlive = true;

    public Animal(String name, int x, int y, AnimalAttributes attributes) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.attributes = Objects.requireNonNull(attributes, "AnimalAttributes cannot be null");

        this.weight = attributes.getWeight();
        this.maxInCell = attributes.getMaxPerCell();
        this.speed = attributes.getMaxMoveDistance();
        this.foodNeed = attributes.getFoodRequire();
        this.satiety = 0;
        this.height = 50.0;
    }


    public boolean isAlive() {
        return isAlive;
    }

    public void dia() {
        this.isAlive = false;
    }

    public abstract void performActions(Cell currentCell, List<Animal> animalsInCell);

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSatiated() {
        return satiety >= foodNeed;
    }

    public void increaseSatiety(double amount) {
        satiety = Math.max(satiety + amount, foodNeed);
    }

    public void move(Cell currentCell) {
        int newX = clamp(currentCell.getX() + randomStep(), 0, currentCell.getIsland().getWidth() - 1);
        int newY = clamp(currentCell.getY() + randomStep(), 0, currentCell.getIsland().getHeight() - 1);
        setPosition(newX, newY);
    }

    private int randomStep() {
        if (speed < 0) {
            throw new IllegalArgumentException("speed is negative");
        }
        return RANDOM.nextInt(speed + 1);
    }

    public void eat(Animal prey) {
        increaseSatiety(prey.weight);
    }

    public void checkHealth(Cell cell) {
        if (cell.getPlantMass() <= 0) {
            dia();
        }
    }

    @Override
    public String toString() {
        return name + "at (" + x + ", " + y + ")";
    }
}
