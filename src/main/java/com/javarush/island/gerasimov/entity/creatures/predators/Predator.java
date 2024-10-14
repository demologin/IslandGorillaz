package com.javarush.island.gerasimov.entity.creatures.predators;

import com.javarush.island.gerasimov.entity.creatures.Animal;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.herbivores.*;
import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Predator extends Animal {

    public static int eatCounter = 0;
    Organism sacrifice;
    List<Organism> foodForPredator = new ArrayList<>();
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

    @Override
    public synchronized boolean eat() {
        foodForPredator = getFoodForPredator();
        sacrifice = foodForPredator.get((int) (Math.random() * foodForPredator.size()));
        switch (sacrifice) {
            case Horse horse -> eating(horse, this.getProbabilityEatenHorse());
            case Buffalo buffalo -> eating(buffalo, this.getProbabilityEatenBuffalo());
            case Deer deer -> eating(deer, this.getProbabilityEatenDeer());
            case Boar boar -> eating(boar, this.getProbabilityEatenBoar());
            case Sheep sheep -> eating(sheep, this.getProbabilityEatenSheep());
            case Goat goat -> eating(goat, this.getProbabilityEatenGoat());
            case Duck duck -> eating(duck, this.getProbabilityEatenDuck());
            case Mouse mouse -> eating(mouse, this.getProbabilityEatenMouse());
            case Rabbit rabbit -> eating(rabbit, this.getProbabilityEatenRabbit());
            case Caterpillar caterpillar -> eating(caterpillar, this.getProbabilityEatenCaterpillar());
            case Bear bear -> eating(bear, this.getProbabilityEatenBear());
            case Eagle eagle -> eating(eagle, this.getProbabilityEatenEagle());
            case Fox fox -> eating(fox, this.getProbabilityEatenFox());
            case Snake snake -> eating(snake, this.getProbabilityEatenSnake());
            case Wolf wolf -> eating(wolf, this.getProbabilityEatenWolf());

            default -> throw new IllegalStateException("Unexpected value: " + sacrifice);
        }
        eatCounter++;
        return true;
    }

    private synchronized List<Organism> getFoodForPredator() {
        List<Organism> foodForPredator = new ArrayList<>();
        Cell targetCell = this.getTargetCell();
        for (Organism organism : targetCell.getOrganisms()) {
            if (Herbivore.class.isAssignableFrom(organism.getClass())) {
                foodForPredator.add(organism);
            }
        }
        return foodForPredator;
    }

    private synchronized void eating(Organism sacrifice, int probabilityAnyone) {
        Cell targetCell = this.getTargetCell();
        this.sacrifice = sacrifice;
        int probabilityEaten;
        probabilityEaten = (int) (Math.random() * 100);

        if (probabilityEaten <= probabilityAnyone) {
            if (sacrifice.getWeight() > this.getMaxFood()) {
                this.setWeight(this.getWeight() + this.getMaxFood());
                targetCell.getOrganisms().remove(sacrifice);
            } else if (sacrifice.getWeight() < this.getMaxFood()) {
                this.setWeight(this.getWeight() + sacrifice.getWeight());
                targetCell.getOrganisms().remove(sacrifice);

            }

        }
    }
}
