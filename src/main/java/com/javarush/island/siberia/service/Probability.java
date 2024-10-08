package com.javarush.island.siberia.service;

import com.javarush.island.siberia.config.Settings;
import java.util.Map;

public class Probability {

    private static final Settings settings = Settings.getInstance();

    public static int getProbability(String predatorSpecies, String preySpecies) {
        Map<String, Integer> predatorProbabilities = settings.getPredatorProbabilities(predatorSpecies);
        return predatorProbabilities.getOrDefault(preySpecies, 0);
    }

}