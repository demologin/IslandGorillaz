package com.javarush.island.siberia.service;

import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.animals.Animal;
import com.javarush.island.siberia.utils.RandomUtils;
import java.util.List;
import java.util.Map;

/**
 * The EatingBehavior class handles the logic for animals eating other organisms.
 * It uses the probabilities defined in the settings to determine whether a predator
 * successfully consumes prey in the same location.
 */

public class EatingBehavior {

    private final Settings settings = Settings.getInstance();

    /**
     * This method allows an animal to attempt to eat other organisms in its current location.
     * The method retrieves the prey probabilities for the given animal species and determines
     * if the animal can successfully hunt and consume a prey organism.
     *
     * @param animal The animal attempting to eat.
     */

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

    /**
     * Handles the consumption of a prey organism by an animal. The prey is killed,
     * and the animal's food level is increased based on the prey's weight and the animal's
     * maximum food capacity. The animal's hunger is also reset after eating.
     *
     * @param animal The animal consuming the prey.
     * @param prey   The prey organism that is being consumed.
     */

    private void consume(Animal animal, Organism prey) {
        prey.die();
        double foodAmount = Math.min(prey.getWeight(), animal.getMaxFood() - animal.getCurrentFood());
        animal.setCurrentFood(animal.getCurrentFood() + foodAmount);
        animal.setSatiety(animal.getCurrentFood() / animal.getMaxFood());
        animal.resetHunger();
    }

}