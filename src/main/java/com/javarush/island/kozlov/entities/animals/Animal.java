package com.javarush.island.kozlov.entities.animals;


import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.exception.MoveException;
import com.javarush.island.kozlov.exception.ReproductionException;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public abstract class Animal implements AnimalsEat, Movable, Reproduce {
    private final double weight;
    protected final int maxOnCell;
    protected final int speed;
    private double foodNeed;
    private int hungerCycles;
    private boolean bornRecently = false;
    private boolean eatenRecently = false;

    // Конструктор для инициализации основных параметров животного
    public Animal(double weight, int maxOnCell, int speed, double foodNeed) {
        this.weight = weight;
        this.maxOnCell = maxOnCell;
        this.speed = speed;
        this.foodNeed = foodNeed;
    }

    // Получить вес животного
    public double getWeight() {
        return weight;
    }

    // Получить максимальное количество животных на клетке
    public int getMaxOnCell() {
        return maxOnCell;
    }

    // Получить скорость передвижения животного
    public int getSpeed() {
        return speed;
    }

    // Получить потребность в еде
    public double getFoodNeed() {
        return foodNeed;
    }

    // Установить потребность в еде
    public void setFoodNeed(double foodNeed) {
        this.foodNeed = Math.max(foodNeed, 0); // Потребность в пище не может быть меньше 0
    }

    // Обработка голода
    public void resetHungerCycles() {
        this.hungerCycles = 0;
    }

    public void increaseHungerCycles() {
        this.hungerCycles++;
    }

    public int getHungerCycles() {
        return hungerCycles;
    }

    // Логика поедания (реализована в интерфейсе AnimalsEat)
    @Override
    public void eat(Location location) {
        AnimalsEat.super.eat(location);
    }

    // Логика перемещения (реализована в интерфейсе Movable)
    @Override
    public void move(Location currentLocation, Island island) throws MoveException {
        Movable.super.move(currentLocation, island);
    }

    // Логика размножения (реализована в интерфейсе Reproduce)
    @Override
    public void reproduce(Location location) throws ReproductionException {
        Reproduce.super.reproduce(location);
    }

    // Методы для работы со статусом животного
    public void markAsBorn() {
        this.bornRecently = true;
    }

    public void markAsEaten() {
        this.eatenRecently = true;
    }

    public boolean isBornRecently() {
        return bornRecently;
    }

    public boolean isEatenRecently() {
        return eatenRecently;
    }

    public void resetFlags() {
        bornRecently = false;
        eatenRecently = false;
    }
}