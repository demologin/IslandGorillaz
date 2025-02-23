package com.javarush.island.myasnikov.map;

import com.javarush.island.myasnikov.entity.organism.Organism;
import com.javarush.island.myasnikov.entity.organism.OrganismFactory;
import com.javarush.island.myasnikov.utility.OrganismTypes;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.javarush.island.myasnikov.config.Params.*;
import static com.javarush.island.myasnikov.utility.RandomNumberGenerator.getRandomInt;

@Getter
public class GameMap {

    private final Cell[][] map;

    @Getter
    private List<Organism> organismsOnMap = new CopyOnWriteArrayList<>();

    private final HashMap<OrganismTypes, Integer> organismsLimitSpawn = getAnimalsSpawnLimit();

    public GameMap() {
        map = new Cell[SIDE_SIZE][SIDE_SIZE];
        createMap();
        populateMap();
    }

    private void createMap() {
        for (int i = 0; i < SIDE_SIZE; i++) {
            for (int j = 0; j < SIDE_SIZE; j++) {
                map[i][j] = new Cell(this);
            }
        }
        fillNeighbors();
    }

    public Cell[][] getMap() {
        if (map == null) {
            createMap();
        }
        return map;
    }

    private void fillNeighbors() {
        for (int i = 0; i < SIDE_SIZE; i++) {
            for (int j = 0; j < SIDE_SIZE; j++) {
                for (int[] offset : MOVE_TO_CELLS) {
                    int neighborI = i + offset[0];
                    int neighborJ = j + offset[1];

                    if (neighborI >= 0 && neighborI < SIDE_SIZE && neighborJ >= 0 && neighborJ < SIDE_SIZE) {
                        map[i][j].addNeighborCell(map[neighborI][neighborJ]);
                    }
                }
            }
        }

    }

    public void addOrganismOnMap(Organism organism) {
        organismsOnMap.add(organism);
    }

    public void removeOrganismsFromMap(Organism organism) {
        organismsOnMap.remove(organism);
    }

    public void populateMap() {
        OrganismFactory organismFactory = new OrganismFactory();

        for (OrganismTypes type : OrganismTypes.values()) {
            int organismLimit = organismsLimitSpawn.get(type);

            for (int i = organismLimit; i > 0; i--) {
                int randomCellX = getRandomInt(0, SIDE_SIZE);
                int randomCellY = getRandomInt(0, SIDE_SIZE);
                Organism organism = organismFactory.createOrganism(type);
                Cell populuableCell = map[randomCellX][randomCellY];
                populuableCell.addOrganism(organism);
                organism.setCurrentCell(populuableCell);
                addOrganismOnMap(organism);
            }

        }
    }

    public void getStatistics() {
        HashMap<String, Integer> animalCount = new HashMap<>();

        for (Organism organism : organismsOnMap) {
            if (organism.isAlive()) {
                animalCount.put(organism.getIcon(), animalCount.getOrDefault(organism.getIcon(), 0) + 1);
            }
        }

        for (java.util.Map.Entry<String, Integer> entry : animalCount.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

}
