package com.javarush.island.siberia.service;

import com.javarush.island.siberia.config.Settings;
import java.util.Map;

/**
 * The Probability class is responsible for determining the likelihood of carnivores successfully hunting prey.
 * It retrieves the predefined probabilities from the settings configuration and performs chance calculations for hunting.
 */

public class Probability {

    private static final Settings settings = Settings.getInstance();

    /**
     * Retrieves the probability of a predator successfully hunting a specific prey species.
     *
     * @param predatorSpecies The name of the predator species.
     * @param preySpecies     The name of the prey species.
     * @return The probability (in percentage) of the predator hunting the prey.
     */

    public static int getProbability(String predatorSpecies, String preySpecies) {
        Map<String, Integer> predatorProbabilities = settings.getPredatorProbabilities(predatorSpecies);
        return predatorProbabilities.getOrDefault(preySpecies, 0);
    }

}