package com.javarush.island.gerasimov.repository;

import com.javarush.island.gerasimov.constants.Constants;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.herbivores.*;
import com.javarush.island.gerasimov.entity.creatures.plants.Grass;
import com.javarush.island.gerasimov.entity.creatures.predators.*;
import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.entity.map.GameMap;
import com.javarush.island.gerasimov.utils.ConfigLoader;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class EntityCreator extends Thread {

    public static final Cell[][] cells = new Cell[Constants.MAP_ROW][Constants.MAP_COL];
    public static GameMap gameMap = new GameMap(cells);
    private final CopyOnWriteArrayList<Cell> listCells = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<CopyOnWriteArrayList<Organism>> listSets = new CopyOnWriteArrayList<>();

    @Override
    public void run() {
        addOrganisms();
    }

    public void createListCell() {
        for (int i = 0; i < Constants.COUNT_CELLS_IN_MAP; i++) {
            Cell cell = new Cell();
            listCells.add(cell);
        }
    }

    public Organism createOrganism() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ConfigLoader configLoader = new ConfigLoader();
        int ran = random.nextInt(Constants.COUNT_ORGANISMS);
        return switch (ran) {
            case 0 -> configLoader.getObject(Horse.class);
            case 1 -> configLoader.getObject(Wolf.class);
            case 2 -> configLoader.getObject(Grass.class);
            case 3 -> configLoader.getObject(Buffalo.class);
            case 4 -> configLoader.getObject(Goat.class);
            case 5 -> configLoader.getObject(Bear.class);
            case 6 -> configLoader.getObject(Eagle.class);
            case 7 -> configLoader.getObject(Fox.class);
            case 8 -> configLoader.getObject(Snake.class);
            case 9 -> configLoader.getObject(Boar.class);
            case 10 -> configLoader.getObject(Caterpillar.class);
            case 11 -> configLoader.getObject(Deer.class);
            case 12 -> configLoader.getObject(Duck.class);
            case 13 -> configLoader.getObject(Mouse.class);
            case 14 -> configLoader.getObject(Rabbit.class);
            case 15 -> configLoader.getObject(Sheep.class);
            default -> throw new AssertionError();
        };
    }

    public void initialisationOrganism() {
        for (int j = 0; j < Constants.COUNT_CELLS_IN_MAP; j++) {
            CopyOnWriteArrayList<Organism> organisms = new CopyOnWriteArrayList<>();
            for (int k = 0; k < Constants.COUNT_ORGANISMS_IN_CELL; k++) {
                organisms.add(createOrganism());
            }
            listSets.add(organisms);
        }
    }

    public void addOrganisms() {
        createListCell();
        initialisationOrganism();

        int cellCounter = 0;
        for (int i = 0; i < Constants.MAP_ROW; i++) {
            for (int j = 0; j < Constants.MAP_COL; j++) {
                cells[i][j] = listCells.get(cellCounter);
                cellCounter++;
            }
        }
        int setCounter = 0;
        for (int i = 0; i < Constants.MAP_ROW; i++) {
            for (int j = 0; j < Constants.MAP_COL; j++) {
                cells[i][j].setOrganisms(listSets.get(setCounter));
                setCounter++;
            }
        }
        gameMap = new GameMap(cells);
    }
}
