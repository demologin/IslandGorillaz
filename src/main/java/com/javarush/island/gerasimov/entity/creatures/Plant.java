package com.javarush.island.gerasimov.entity.creatures;

import com.javarush.island.gerasimov.entity.creatures.plants.Grass;
import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
public abstract class Plant extends Organism implements Cloneable {

    public static int reproduceCounter = 0;
    public static int deadCounter = 0;
    private String name;
    private String icon;
    private double weight;
    private int maxCountInCell;
    private int maxSpeed;
    private double maxFood;
    private Cell currentCell;
    private Cell targetCell;

    @SneakyThrows
    @Override
    public synchronized boolean reproduce(Cell currentCell) {
        currentCell = this.getCurrentCell();
        targetCell = appointmentTargetCell(currentCell);
        int countPlantTargetCell = 0;
        for (Organism organism : targetCell.getOrganisms()) {
            if (organism.getClass().equals(this.getClass())) {
                countPlantTargetCell++;
            }
        }
        if (countPlantTargetCell < this.getMaxCountInCell() && this.getWeight() > 1) {
            Organism organism = (Organism) this.clone();
            targetCell.getOrganisms().add(organism);
            reproduceCounter++;
            return true;
        }
        return false;
    }
}
