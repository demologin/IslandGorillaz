package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@EqualsAndHashCode(callSuper = true)
@Data
public class Duck extends Herbivore {

    private String name = "Утка";
    private String icon = "\uD83E\uDD86";
    private double weight = 1;
    private int maxCountInCell = 200;
    private int maxSpeed = 4;
    private double maxFood = 0.15;
    private Cell targetCell;
    private int probabilityEatenCaterpillar = 40;

    @Override
    public String toString() {
        return getIcon();
    }

    @Override
    public boolean eat() {
        super.eat();
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        List<Organism> foodForDuck = getFoodForDuck();
        int chooseSacrifice = threadLocalRandom.nextInt(foodForDuck.size());
        int probabilityEaten = threadLocalRandom.nextInt(100);
        Organism sacrifice = foodForDuck.get(chooseSacrifice);
        if (probabilityEatenCaterpillar < probabilityEaten) {
            this.setWeight(getWeight() + sacrifice.getWeight());
            targetCell.getOrganisms().add(sacrifice);
            return true;
        }
        return false;
    }

    public List<Organism> getFoodForDuck() {
        List<Organism> foodForDuck = new ArrayList<>();
        Cell targetCell = getTargetCell();
        for (Organism organism : targetCell.getOrganisms()) {
            if (organism instanceof Caterpillar) {
                foodForDuck.add(organism);
            }
        }
        return foodForDuck;
    }
}
