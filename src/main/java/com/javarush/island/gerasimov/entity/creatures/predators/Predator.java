package com.javarush.island.gerasimov.entity.creatures.predators;

import com.javarush.island.gerasimov.entity.creatures.Animal;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.herbivores.*;
import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Predator extends Animal {

    public volatile static int eatCounter = 0;
    public volatile static int deadCounter = 0;
    private int probabilityEatenHorse;
    private int probabilityEatenBuffalo;
    private int probabilityEatenBoar;
    private int probabilityEatenDuck;
    private int probabilityEatenSheep;
    private int probabilityEatenGoat;
    private int probabilityEatenRabbit;
    private int probabilityEatenDeer;
    private int probabilityEatenBear;
    private int probabilityEatenFox;
    private int probabilityEatenSnake;
    private int probabilityEatenEagle;
    private int probabilityEatenMouse;
    private int probabilityEatenWolf;
    private int probabilityEatenCaterpillar;

    /*
    Randomly selects a sacrifice from the list and eats it
     */

    @Override
    public synchronized boolean eat() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<Organism> foodForPredator = getFoodForPredator();
        Organism sacrifice = foodForPredator.get(random.nextInt(foodForPredator.size()));
        if (sacrifice instanceof Horse horse && eatSacrifice(horse, getProbabilityEatenHorse())) {
            return true;
        } else if (sacrifice instanceof Buffalo buffalo && eatSacrifice(buffalo, getProbabilityEatenBuffalo())) {
            return true;
        } else if (sacrifice instanceof Deer deer && eatSacrifice(deer, getProbabilityEatenDeer())) {
            return true;
        } else if (sacrifice instanceof Boar boar && eatSacrifice(boar, getProbabilityEatenBoar())) {
            return true;
        } else if (sacrifice instanceof Sheep sheep && eatSacrifice(sheep, getProbabilityEatenSheep())) {
            return true;
        } else if (sacrifice instanceof Goat goat && eatSacrifice(goat, getProbabilityEatenGoat())) {
            return true;
        } else if (sacrifice instanceof Duck duck && eatSacrifice(duck, getProbabilityEatenDuck())) {
            return true;
        } else if (sacrifice instanceof Mouse mouse && eatSacrifice(mouse, getProbabilityEatenMouse())) {
            return true;
        } else if (sacrifice instanceof Rabbit rabbit && eatSacrifice(rabbit, getProbabilityEatenRabbit())) {
            return true;
        } else if (sacrifice instanceof Caterpillar caterpillar && eatSacrifice(caterpillar, getProbabilityEatenCaterpillar())) {
            return true;
        } else if (sacrifice instanceof Fox fox && eatSacrifice(fox, getProbabilityEatenFox())) {
            return true;
        } else if (sacrifice instanceof Snake snake && eatSacrifice(snake, getProbabilityEatenSnake())) {
            return true;
        } else if (sacrifice instanceof Eagle eagle && eatSacrifice(eagle, getProbabilityEatenEagle())) {
            return true;
        } else if (sacrifice instanceof Bear bear && eatSacrifice(bear, getProbabilityEatenBear())) {
            return true;
        } else if (sacrifice instanceof Wolf wolf && eatSacrifice(wolf, getProbabilityEatenWolf())) {
            return true;
        }
        return false;
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
        Cell targetCell = this.getTargetCell();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int probabilityEaten = random.nextInt(100);
        if (probabilityEaten <= probabilityEatenAnyone) {
            if (sacrifice.getWeight() > this.getMaxFood()) {
                this.setWeight(this.getWeight() + this.getMaxFood());
                targetCell.getOrganisms().remove(sacrifice);
                eatCounter++;
                return true;
            } else if (sacrifice.getWeight() < getMaxFood()) {
                setWeight(getWeight() + sacrifice.getWeight());
                targetCell.getOrganisms().remove(sacrifice);
                eatCounter++;
                return true;
            }
        }
        return false;
    }
}
