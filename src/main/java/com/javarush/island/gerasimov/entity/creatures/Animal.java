package com.javarush.island.gerasimov.entity.creatures;

import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.intefaces.EatAble;
import com.javarush.island.gerasimov.intefaces.MoveAble;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public abstract class Animal extends Organism implements MoveAble, EatAble, Cloneable {

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
        targetCell = appointmentTargetCell(getCurrentCell());
        int countOrganism = 0;
        for (Organism organism : targetCell.getOrganisms()) {
            if (organism.getClass().equals(this.getClass())) {
                countOrganism++;
            }
        }
        if (countOrganism < this.getMaxCountInCell()) {
                targetCell.getOrganisms().add(this);
                currentCell.getOrganisms().remove(this);
            moveCounter++;
                return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    public synchronized boolean reproduce(Cell targetCell) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ArrayList<Organism> organismsForReproduce = new ArrayList<>();
        targetCell = appointmentTargetCell(getCurrentCell());
        int probabilityReproduce = random.nextInt(100);
        int counter = 0;
        for (Organism organism : targetCell.getOrganisms()) {
            if (organism.getClass().equals(this.getClass())) {
                organismsForReproduce.add(organism);
                counter++;
            }
        }
        int chooseAnimal = random.nextInt(organismsForReproduce.size());
        if ((!organismsForReproduce.get(chooseAnimal).equals(this)) &&
                probabilityReproduce < 30 &&
                counter < this.getMaxCountInCell()) {
            Organism newAnimal = (Organism) this.clone();
            newAnimal.setWeight(this.getWeight() / 3);
            targetCell.getOrganisms().add(newAnimal);
            reproduceCounter++;
            return true;
        }
        return false;
    }
}


