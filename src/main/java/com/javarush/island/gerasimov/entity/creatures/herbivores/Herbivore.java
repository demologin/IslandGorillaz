package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.creatures.Animal;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.entity.map.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Herbivore extends Animal {

    public static int eatCounter = 0;
    @Override
    public boolean eat() {

        Cell targetCell = this.getTargetCell();
        Organism sacrifice;
        List<Organism> foodForHerbivores = new ArrayList<>();
        for (Organism organism : targetCell.getOrganisms()) {
            if (Plant.class.isAssignableFrom(organism.getClass())) {
                foodForHerbivores.add(organism);
            }
        }
        sacrifice = foodForHerbivores.get(new Random().nextInt(foodForHerbivores.size()));
        if (sacrifice.getWeight() > this.getMaxFood()) {
            this.setWeight(this.getWeight() + this.getMaxFood());
            sacrifice.setWeight(sacrifice.getWeight() - this.getMaxFood());
            if (Math.round(sacrifice.getWeight()) >= 0) {
                this.getTargetCell().getOrganisms().remove(sacrifice);

            } else if (sacrifice.getWeight() < this.getMaxFood()) {
                this.setWeight(this.getWeight() + sacrifice.getWeight());
                this.getTargetCell().getOrganisms().remove(sacrifice);
            }
        }
        eatCounter++;
        return true;
    }
}
