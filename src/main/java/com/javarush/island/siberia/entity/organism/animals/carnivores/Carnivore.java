package com.javarush.island.siberia.entity.organism.animals.carnivores;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.animals.Animal;
import com.javarush.island.siberia.service.Probability;
import com.javarush.island.siberia.utils.RandomUtils;

import java.util.List;

public abstract class Carnivore extends Animal {
    public Carnivore(Location location) {
        super(location);
    }

    @Override
    public void eat() {
        List<Organism> organisms = this.getLocation().getOrganisms();
        for (Organism organism : organisms) {
            if (organism != this && organism.isAlive() && isPrey(organism)) {
                if (attemptToEat(organism)) {
                    consume(organism);
                    break;
                }
            }
        }
    }

    protected boolean isPrey(Organism organism) {
        String preySpecies = organism.getClass().getSimpleName();
        int probability = Probability.getProbability(this.getClass().getSimpleName(), preySpecies);
        return probability > 0;
    }

    protected boolean attemptToEat(Organism prey) {
        String preySpecies = prey.getClass().getSimpleName();
        int probability = Probability.getProbability(this.getClass().getSimpleName(), preySpecies);
        return RandomUtils.chance(probability);
    }

    protected void consume(Organism prey) {
        prey.die();
        double foodAmount = Math.min(prey.getWeight(), this.getMaxFood() - this.getCurrentFood());
        this.setCurrentFood(this.getCurrentFood() + foodAmount);
        this.setSatiety(this.getCurrentFood() / this.getMaxFood());
    }

}