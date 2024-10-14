package com.javarush.island.gerasimov.entity.creatures;

import com.javarush.island.gerasimov.entity.creatures.grass.Grass;
import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Plant extends Organism {

    public static int reproduceCounter = 0;
    private String name;
    private String icon;
    private double weight;
    private int maxCountInCell;
    private int maxSpeed;
    private double maxFood;
    private Cell currentCell;
    private Cell targetCell;

    @Override
    public synchronized boolean reproduce(Cell currentCell) {
        targetCell = appointmentTargetCell(this.getCurrentCell());
        currentCell = this.getCurrentCell();
        int countPlantCurrentCell = 0;
        for (Organism organism : currentCell.getOrganisms()) {
            if (organism.getClass().equals(this.getClass())) {
                countPlantCurrentCell++;
            }
        }
        int countPlantTargetCell = 0;
        for (Organism organism : targetCell.getOrganisms()) {
            if (organism.getClass().equals(this.getClass())) {
                countPlantTargetCell++;
            }
        }
        if (countPlantCurrentCell < this.getMaxCountInCell()) {
            Organism organism = new Grass();
            currentCell.getOrganisms().add(organism);
        } else {
            if (countPlantTargetCell < this.getMaxCountInCell()) {
                Organism organism = new Grass();
                targetCell.getOrganisms().add(organism);
            }
            return false;
        }
        reproduceCounter++;
        return true;
    }
}
