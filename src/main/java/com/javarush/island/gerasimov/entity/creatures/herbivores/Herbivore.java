package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.creatures.Animal;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Herbivore extends Animal {

    public volatile static int eatCounter = 0;

    /*
    Randomly selects a sacrifice from the list and eats it
     */

    @Override
    public synchronized boolean eat() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<Organism> foodForHerbivores = getTargetCell().getOrganisms();
        Organism sacrifice = foodForHerbivores.get(random.nextInt(foodForHerbivores.size()));
        return eatPlant(sacrifice, getLikelyFood().get(sacrifice.getName()));
    }

    /*
    Checks whether the predator can eat the sacrifice and eats it
     */

    private synchronized boolean eatPlant(Organism sacrifice, int probabilityEatenAnyone) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int probabilityEaten = random.nextInt(100);
        if (probabilityEaten <= probabilityEatenAnyone) {
            if (sacrifice.getWeight() > this.getMaxFood()) {
                this.setWeight(this.getWeight() + this.getMaxFood());
                sacrifice.setWeight(sacrifice.getWeight() - this.getMaxFood());
                if (Math.round(sacrifice.getWeight()) <= 0) {
                    this.getTargetCell().getOrganisms().remove(sacrifice);
                    eatCounter++;
                }
                return true;
            } else if (sacrifice.getWeight() < this.getMaxFood()) {
                this.setWeight(getWeight() + sacrifice.getWeight());
                this.getTargetCell().getOrganisms().remove(sacrifice);
                eatCounter++;
                return true;
            }
        }
        return false;
    }
}
