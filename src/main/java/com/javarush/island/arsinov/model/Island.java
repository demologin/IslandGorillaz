package com.javarush.island.arsinov.model;

import com.javarush.island.arsinov.model.animals.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Island {
    private final Cell[][] map;
    private final ExecutorService executor;

    private final int width;
    private final int height;

    public Island(int width, int height, int initialPlantsPerCell) {
        this.width = width;
        this.height = height;
        map = new Cell[width][height];
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new Cell(i,j,this,initialPlantsPerCell);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Coordinates outside the island boundaries: " + x + ", " + y);
        }
        return map[x][y];
    }

    public List<Cell> getCells(){
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells.add(map[i][j]);
            }
        }
        return cells;
    }

    public void step(){
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                int finalI = i;
                int finalJ = j;

                futures.add(executor.submit(() -> {
                    Cell cell = map[finalI][finalJ];

                    for(Animal animal : cell.getAnimals()){
                        animal.checkHealth(cell);
                    }

                    cell.removeDeadAnimals();

                    for(Animal animal : cell.getAnimals()){
                        animal.performActions(cell,cell.getAnimals());
                    }
                    cell.growPlants();
                }));
            }
        }
        for (Future<?> future : futures) {
            try{
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        executor.shutdown();
    }
}
