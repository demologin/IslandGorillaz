package com.javarush.island.nikitin.application.config;

import com.javarush.island.nikitin.application.constants.FailMessagesApp;
import com.javarush.island.nikitin.application.exception.AppException;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMap;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TODO в классе лежат стратовые настройки игры - размер острова, талблица любимой еды животных. Коэфф заполнения при старте
// все параметры должны перезаписыватся с файла конфигурации yaml
public class DefaultSettings {
    //стартовый коэф заполнения локации животными
    public static double START_OCCUPANCY_RATE = 0.5d;
    public static final int ROWS = 5;
    public static final int COLUMNS = 5;

    private final static Map<String, PreferenceMap> PREFERENCE = new HashMap<>();
    public static final String[] ANIMAL_TYPE = {"Wolf", "Boa", "Fox", "Bear", "Eagle", "Horse", "Deer",
            "Rabbit", "Mouse", "Goat", "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar", "Grass"};
    public static final int[][] PROBABILITY_OF_EATING = {
            {0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0, 0},
            {0, 0, 15, 0, 0, 0, 0, 20, 40, 0, 0, 0, 0, 10, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 70, 90, 0, 0, 0, 0, 60, 40, 0},
            {0, 80, 0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0, 0},
            {0, 0, 10, 0, 0, 0, 0, 90, 90, 0, 0, 0, 0, 80, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private DefaultSettings() {
    }

    public static PreferenceMap getPreferenceMapForUnit(String unitName) {
        if (PREFERENCE.isEmpty()) {
            verifyArraysDataConsistency();
            makePreferenceMap111();
        }
        if (PREFERENCE.containsKey(unitName))
            return PREFERENCE.get(unitName);
        else {
            throw new AppException(FailMessagesApp.NO_FOOD_FOR_THIS_TYPE);
        }
    }

    private static void makePreferenceMap111() {
        for (int i = 0; i < PROBABILITY_OF_EATING.length; i++) {
            int[] targetArray = PROBABILITY_OF_EATING[i];
            String currentAnimalType = ANIMAL_TYPE[i];
            PreferenceMap preferenceMap = makePreferenceMap(targetArray);
            PREFERENCE.put(currentAnimalType, preferenceMap);
        }
    }

    private static PreferenceMap makePreferenceMap(int[] targetArray) {
        Map<String, Integer> foodChoiceProbabilities = IntStream.range(0, targetArray.length)
                .filter(j -> targetArray[j] != 0)
                .boxed()
                .collect(Collectors.toMap(
                        j -> ANIMAL_TYPE[j],
                        j -> targetArray[j]
                ));
        return new PreferenceMap(foodChoiceProbabilities);
    }

    private static void verifyArraysDataConsistency() {
        if (ANIMAL_TYPE.length != PROBABILITY_OF_EATING.length) {
            throw new AppException(FailMessagesApp.DIFFERENT_LENGTHS);
        }
    }
}
