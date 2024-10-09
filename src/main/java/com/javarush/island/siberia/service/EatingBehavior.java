package com.javarush.island.siberia.service;

import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.animals.Animal;
import com.javarush.island.siberia.utils.RandomUtils;

import java.util.List;
import java.util.Map;

public class EatingBehavior {

    private final Settings settings = Settings.getInstance();

    public void eat(Animal animal) {
        List<Organism> organisms = animal.getLocation().getOrganisms();
        Map<String, Integer> preyProbabilities = settings.getPredatorProbabilities(animal.getClass().getSimpleName());

        for (Organism prey : organisms) {
            if (prey != animal && prey.isAlive()) {
                String preySpecies = prey.getClass().getSimpleName();

                if (preyProbabilities.containsKey(preySpecies)) {
                    int probability = preyProbabilities.get(preySpecies);
                    if (RandomUtils.chance(probability)) {
                        consume(animal, prey);
                        break;
                    }
                }
            }
        }
    }

    private void consume(Animal animal, Organism prey) {
        prey.die();
        double foodAmount = Math.min(prey.getWeight(), animal.getMaxFood() - animal.getCurrentFood());
        animal.setCurrentFood(animal.getCurrentFood() + foodAmount);
        animal.setSatiety(animal.getCurrentFood() / animal.getMaxFood());
        animal.resetHunger();
    }

}