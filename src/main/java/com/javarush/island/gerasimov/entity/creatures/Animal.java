package com.javarush.island.gerasimov.entity.creatures;

import com.javarush.island.gerasimov.entity.creatures.herbivores.Herbivore;
import com.javarush.island.gerasimov.entity.creatures.predators.Predator;
import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.intefaces.EatAble;
import com.javarush.island.gerasimov.intefaces.MoveAble;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public abstract class Animal extends Organism implements MoveAble, EatAble, Cloneable {

    static volatile public int moveCounter = 0;
    static volatile public int reproduceCounter = 0;

    @Override
    public synchronized boolean move() {
        int countOrganism = 0;
        for (Organism organism : getTargetCell().getOrganisms()) { //!!!
            if (organism.getClass().equals(this.getClass())) {
                countOrganism++;
            }
        }
        if (countOrganism < this.getMaxCountInCell()) {
                getTargetCell().getOrganisms().add(this); // !!!
                this.getCurrentCell().getOrganisms().remove(this);
            moveCounter++;
                return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    public synchronized boolean reproduce() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ArrayList<Organism> organismsForReproduce = new ArrayList<>();
        int probabilityReproduce = random.nextInt(100);
        int counter = 0;
        for (Organism organism : getTargetCell().getOrganisms()) {
            if (organism.getClass().equals(this.getClass())) {
                organismsForReproduce.add(organism);
                counter++;
            }
        }
        int chooseAnimal = random.nextInt(organismsForReproduce.size());
        if ((!organismsForReproduce.get(chooseAnimal).equals(this)) &&
                probabilityReproduce < 50 &&
                counter < this.getMaxCountInCell()) {
            Organism newAnimal = (Organism) this.clone();
            newAnimal.setWeight(this.getWeight() / 3);
            getTargetCell().getOrganisms().add(newAnimal);
            reproduceCounter++;
            return true;
        }
        return false;
    }
}


