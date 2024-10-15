package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.herbivores.*;
import com.javarush.island.gerasimov.entity.creatures.plants.Grass;
import com.javarush.island.gerasimov.entity.creatures.predators.*;
import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.entity.map.GameMap;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class EntityCreator extends Thread {

    public static final Cell[][] cells = new Cell[20][100];
    public static GameMap gameMap = new GameMap(cells);
    private final CopyOnWriteArrayList<Cell> cellsList = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<CopyOnWriteArrayList<Organism>> organismSets = new CopyOnWriteArrayList<>();

    public void createListCell() {
        for (int i = 0; i < 2000; i++) {
            Cell cell = new Cell();
            cellsList.add(cell);
        }
    }

    public Organism createOrganism() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int ran = random.nextInt(16);
        return switch (ran) {
            case 0 -> new Horse();
            case 1 -> new Wolf();
            case 2 -> new Grass();
            case 3 -> new Buffalo();
            case 4 -> new Goat();
            case 5 -> new Bear();
            case 6 -> new Eagle();
            case 7 -> new Fox();
            case 8 -> new Snake();
            case 9 -> new Boar();
            case 10 -> new Caterpillar();
            case 11 -> new Deer();
            case 12 -> new Duck();
            case 13 -> new Mouse();
            case 14 -> new Rabbit();
            case 15 -> new Sheep();
            default -> throw new AssertionError();
        };
    }

    public void initialisationOrganism() {
        for (int j = 0; j < 2000; j++) {
            CopyOnWriteArrayList<Organism> organisms = new CopyOnWriteArrayList<>();
            for (int k = 0; k < 10; k++) {
                organisms.add(createOrganism());
            }
            organismSets.add(organisms);
        }
    }

    public void addOrganisms() {
        createListCell();
        initialisationOrganism();

        int a = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 100; j++) {
                cells[i][j] = cellsList.get(a);
                a++;
            }
        }
        int b = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 100; j++) {
                cells[i][j].setOrganisms(organismSets.get(b));
                b++;
            }
        }
        gameMap = new GameMap(cells);
    }

    @Override
    public void run() {
        addOrganisms();
    }
}
