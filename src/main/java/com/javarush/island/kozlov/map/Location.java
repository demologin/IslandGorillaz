package com.javarush.island.kozlov.map;


import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.plants.Vegetation;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Location {
    private static final int MAX_ANIMALS_ON_CELL = 100;  // Максимальное количество животных на клетке
    private static final int MAX_PLANTS = 200;  // Максимальное количество растений на клетке

    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private final List<Vegetation> plants = new CopyOnWriteArrayList<>();

    private int plantsGrown = 0;  // Количество выращенных растений
    private int plantsEaten = 0;  // Количество съеденных растений

    private final int x;  // Координата x
    private final int y;  // Координата y

    // Параметрический конструктор, который задает координаты локации
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Возвращает координаты локации
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Возвращает максимальное количество животных, которое может находиться на клетке
    public int getMaxOnCell() {
        return MAX_ANIMALS_ON_CELL;
    }

    // Возвращает животных, находящихся на локации
    public List<Animal> getAnimals() {
        return animals;
    }

    // Добавление животного на локацию
    public void addAnimal(Animal animal) {
        if (animals.size() < MAX_ANIMALS_ON_CELL) {
            animals.add(animal);
        } else {
            System.out.println("Location is full. Cannot add more animals.");
        }
    }

    // Удаление животного с локации
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    // Логика роста растений
    public void growPlants() {
        if (plants.size() < MAX_PLANTS) {
            plants.add(new Vegetation(5.0));  // Создаем новое растение с определенной питательной ценностью
            plantsGrown++;
            System.out.println("A new plant has grown. Total plants grown: " + plantsGrown);
        }
    }

    // Логика поедания растений
    public void eatPlant() {
        if (!plants.isEmpty()) {
            plants.remove(0);
            plantsEaten++;
            System.out.println("A plant has been eaten. Total plants eaten: " + plantsEaten);
        }
    }

    // Возвращает список растений на локации
    public List<Vegetation> getPlants() {
        return plants;
    }

    // Возвращает количество выращенных растений
    public int getPlantsGrown() {
        return plantsGrown;
    }

    // Возвращает количество съеденных растений
    public int getPlantsEaten() {
        return plantsEaten;
    }
}
