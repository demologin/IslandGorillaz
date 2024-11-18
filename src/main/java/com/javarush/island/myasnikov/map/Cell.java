package com.javarush.island.myasnikov.map;

import com.javarush.island.myasnikov.entity.organism.Organism;
import lombok.Getter;

import java.util.*;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.javarush.island.myasnikov.config.Params.CELL_VIEW_LIMIT;

@Getter
public class Cell {

    @Getter
    private final GameMap gameMap;

    private List<Organism> cellOrganisms = new CopyOnWriteArrayList<>();

    private final List<Cell> neighborCells = new CopyOnWriteArrayList<>();

    public void addNeighborCell (Cell cell) {
        neighborCells.add(cell);
    }

    public Cell(Organism organism, GameMap gameMap) {
        cellOrganisms.add(organism);
        this.gameMap = gameMap;
    }

    public Cell(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public String getOrganismsIcon() {
        Map<String, Integer> countMap = new HashMap<>();

        for (Organism item : cellOrganisms) {
            String icon = item.getIcon();
            countMap.put(icon, countMap.getOrDefault(icon, 0) + 1);
        }

        var sortedListOfPairs = new ArrayList<>(countMap.entrySet());
        sortedListOfPairs.sort(Comparator.comparingInt(Map.Entry::getValue));

        var result = new StringBuilder();
        for (int i = 0; i < Math.min(CELL_VIEW_LIMIT, sortedListOfPairs.size()); i++) {
            Map.Entry<String, Integer> entry = sortedListOfPairs.get(i);
            result.append(entry.getKey());
        }
        return result.toString();
    }

    public synchronized boolean ifEnoughRoomInCellToReproduce(Organism organism) {
        int countThisAnimalInCell = 0;
        for (Organism animal: getCellOrganisms()) {
            if (animal.getType().equals(organism.getType())) {
                countThisAnimalInCell++;
            }
        }

        return countThisAnimalInCell < organism.getLimit().getMaxCellAmount();
    }


    public synchronized void removeOrganism(Organism organism) {
        this.cellOrganisms.remove(organism);
    }

    public synchronized void addOrganism (Organism organism) {
        this.cellOrganisms.add(organism);
        this.gameMap.addOrganismOnMap(organism);
    }

}
