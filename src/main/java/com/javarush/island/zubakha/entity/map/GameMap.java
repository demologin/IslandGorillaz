package com.javarush.island.zubakha.entity.map;

import com.javarush.island.zubakha.entity.Plant;
import com.javarush.island.zubakha.entity.Animal;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private Cell[][] cells;
    @Getter
    private final int row = 5;
    @Getter
    private final int col = 5;
    private static volatile GameMap instance;

    private GameMap() {
    }

    public static GameMap getInstance() {
        if (instance == null) {
            synchronized (GameMap.class) {
                if (instance == null) {
                    instance = new GameMap();
                }
            }
        }
        return instance;
    }


    public void createMap(int row, int col) {
        cells = new Cell[row][col];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void createMap() {
        cells = new Cell[row][col];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public synchronized Cell getLocation(int row, int col) {
        return cells[row][col];
    }

    public void addAnimal(Animal animal, int row, int col) {
        Cell cell = getLocation(row, col);
        cell.addAnimal(animal);
    }

    public void removeAnimal(Animal animal, int row, int col) {
        Cell cell = getLocation(row, col);
        cell.removeAnimal(animal);
    }

    public void addPlant(Plant plant, int row, int col) {
        Cell cell = getLocation(row, col);
        cell.addPlant(plant);
    }

    public void removePlant(Plant plant, int row, int col) {
        Cell cell = getLocation(row, col);
        cell.removePlant(plant);
    }

    public synchronized List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                allAnimals.addAll(cell.getAnimals());
            }
        }
        return allAnimals;
    }

    public List<Plant> getAllPlants() {
        List<Plant> allPlants = new ArrayList<>();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                allPlants.addAll(cell.getPlants());
            }
        }
        return allPlants;
    }

}
