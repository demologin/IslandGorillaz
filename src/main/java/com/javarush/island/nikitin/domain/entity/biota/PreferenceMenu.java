package com.javarush.island.nikitin.domain.entity.biota;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public record PreferenceMenu(Map<String, Integer> foodChoiceProbabilities) {

    public Optional<Map.Entry<String, Integer>> getAnyItemMenu() {
        int sizeMap = foodChoiceProbabilities.size();
        int random = ThreadLocalRandom.current().nextInt(sizeMap);

        return foodChoiceProbabilities()
                .entrySet()
                .stream()
                .skip(random)
                .findFirst();
    }
}
