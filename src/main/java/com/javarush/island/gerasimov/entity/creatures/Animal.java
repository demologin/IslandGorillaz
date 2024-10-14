package com.javarush.island.gerasimov.entity.creatures;

import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.intefaces.EatAble;
import com.javarush.island.gerasimov.intefaces.MoveAble;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public abstract class Animal extends Organism implements MoveAble, EatAble {

    static public int moveCounter = 0;
    static public int reproduceCounter = 0;
    private String name;
    private String icon;
    private double weight;
    private int maxCountInCell;
    private int maxSpeed;
    private double maxFood;
    private Cell currentCell;

    private Cell targetCell;

    @Override
    public synchronized boolean move(Cell targetCell) {
        targetCell = appointmentTargetCell(this.getCurrentCell());
        int countOrganism = 0;
        for (Organism organism : targetCell.getOrganisms()) {
            if (organism.getClass().equals(this.getClass())) {
                countOrganism++;
            }
        }
        if (countOrganism < this.getMaxCountInCell()) {
            try {
                targetCell.getOrganisms().add(this);
                currentCell.getOrganisms().remove(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        moveCounter++;
        return true;
    }


    @Override
    public synchronized boolean reproduce(Cell targetCell) {
        targetCell = appointmentTargetCell(this.getCurrentCell());
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ArrayList<Organism> organismsForReproduce = new ArrayList<>();
        int probabilityReproduce = random.nextInt(100);
        int counter = 0;
        for (Organism organism : targetCell.getOrganisms()) {
            if (organism.getClass().equals(this.getClass())) {
                organismsForReproduce.add(organism);
                counter++;
            }
        }
        int chooseAnimal = random.nextInt(organismsForReproduce.size());

        if ((!organismsForReproduce.get(chooseAnimal).equals(this)) && probabilityReproduce < 30 && counter < this.getMaxCountInCell()) {
            Organism newAnimal = this;
            newAnimal.setWeight(this.getWeight() / 3);
            targetCell.getOrganisms().add(newAnimal);
        }
        reproduceCounter++;
        return true;
    }
}


