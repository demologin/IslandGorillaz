package com.javarush.island.siberia2.entity.map;

import com.javarush.island.siberia2.entity.animals.Animal;
import lombok.Getter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Cell {

    private final int x;
    private final int y;
    private boolean isWater;
    @Getter
    private final Island island;
    @Getter
    private final Queue<Animal> animals = new ConcurrentLinkedQueue<>();

    public Cell(int x, int y, boolean isWater, Island island) {
        this.x = x;
        this.y = y;
        this.isWater = isWater;
        this.island = island;
    }

    public Collection<Animal> getAnimals() {
        return Collections.unmodifiableCollection(new ArrayList<>(animals));
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public boolean isWater() {
        return isWater;
    }

    public List<Cell> getAdjacentCells() {
        List<Cell> neighbors = new ArrayList<>();
        int width = island.getWidth();
        int height = island.getHeight();

        if (x > 0) {
            neighbors.add(island.getCell(x - 1, y)); //⬅️
            if (y > 0) {
                neighbors.add(island.getCell(x - 1, y - 1)); //↖️
            }
            if (y < height - 1) {
                neighbors.add(island.getCell(x - 1, y + 1)); //↙️
            }
        }
        if (x < width - 1) {
            neighbors.add(island.getCell(x + 1, y)); //➡️
            if (y > 0) {
                neighbors.add(island.getCell(x + 1, y - 1)); //↗️
            }
            if (y < height - 1) {
                neighbors.add(island.getCell(x + 1, y + 1)); //↘️
            }
        }
        if (y > 0) {
            neighbors.add(island.getCell(x, y - 1)); //⬆️
        }
        if (y < height - 1) {
            neighbors.add(island.getCell(x, y + 1)); //⬇️
        }

        return neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        return y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}