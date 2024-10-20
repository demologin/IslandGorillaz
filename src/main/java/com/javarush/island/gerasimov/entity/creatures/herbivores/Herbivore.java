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

    @Override
    public synchronized boolean eat() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<Organism> foodForHerbivores = getFoodForHerbivores();
        int ran = random.nextInt(foodForHerbivores.size());
        Organism sacrifice;
        if (!foodForHerbivores.isEmpty()) {
            sacrifice = foodForHerbivores.get(ran);
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

    public synchronized List<Organism> getFoodForHerbivores() {
        List<Organism> foodForHerbivores = new ArrayList<>();
        for (Organism organism : getTargetCell().getOrganisms()) {
            if (Plant.class.isAssignableFrom(organism.getClass())) {
                foodForHerbivores.add(organism);
            }
        }
        return foodForHerbivores;
    }
}
