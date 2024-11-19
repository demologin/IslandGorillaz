package com.javarush.island.nikitin.domain.entity.biota;

import java.util.Map;

public class PreferenceMap {
    public final Map<String, Integer> foodChoiceProbabilities;

    public PreferenceMap(Map<String, Integer> foodChoiceProbabilities) {
        this.foodChoiceProbabilities = foodChoiceProbabilities;
    }

    public int returnProbability(String targetFood) {
        return foodChoiceProbabilities.get(targetFood);
    }
}
