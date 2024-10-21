package com.javarush.island.gerasimov.entity.creatures.predators;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.javarush.island.gerasimov.entity.creatures.Animal;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.herbivores.*;
import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Predator extends Animal {

    public volatile static int eatCounter = 0;

    /*
    Randomly selects a sacrifice from the list and eats it
     */

    @Override
    public synchronized boolean eat() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<Organism> foodForPredator = getFoodForPredator();
        Organism sacrifice = foodForPredator.get(random.nextInt(foodForPredator.size()));
        return eatSacrifice(sacrifice, getLikelyFood().get(sacrifice.getName()));
    }

    private synchronized List<Organism> getFoodForPredator() {
        List<Organism> foodForPredator = new ArrayList<>();
        Cell targetCell = getTargetCell();
        for (Organism organism : targetCell.getOrganisms()) {
            if (Animal.class.isAssignableFrom(organism.getClass())) {
                foodForPredator.add(organism);
            }
        }
        return foodForPredator;
    }

    /*
    Checks whether the predator can eat the sacrifice and eats it
     */

    private synchronized boolean eatSacrifice(Organism sacrifice, int probabilityEatenAnyone) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int probabilityEaten = random.nextInt(100);
        if (probabilityEaten <= probabilityEatenAnyone) {
            if (sacrifice.getWeight() > this.getMaxFood()) {
                this.setWeight(this.getWeight() + this.getMaxFood());
                getTargetCell().getOrganisms().remove(sacrifice);
                eatCounter++;
                return true;
            } else if (sacrifice.getWeight() < getMaxFood()) {
                setWeight(getWeight() + sacrifice.getWeight());
                getTargetCell().getOrganisms().remove(sacrifice);
                eatCounter++;
                return true;
            }
        }
        return false;
    }
}
