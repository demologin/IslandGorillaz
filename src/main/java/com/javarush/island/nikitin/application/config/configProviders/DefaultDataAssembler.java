package com.javarush.island.nikitin.application.config.configProviders;

import com.javarush.island.nikitin.application.config.DefaultData;
import com.javarush.island.nikitin.application.constants.FailMessagesApp;
import com.javarush.island.nikitin.application.exception.AppException;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DefaultDataAssembler {

    public static <T, R> Map<String, R> collectData(String[] animalType, T[][] arrayData, Function<T[], R> function) {
        verifyArraysDataConsistency(animalType, arrayData);
        HashMap<String, R> map = new HashMap<>();
        for (int i = 0; i < arrayData.length; i++) {
            T[] targetDataForType = arrayData[i];
            R preferenceMap = function.apply(targetDataForType);
            String currentAnimalType = animalType[i];
            map.put(currentAnimalType, preferenceMap);
        }
        return map;
    }

    public static PreferenceMenu makePreferenceMenu(Integer[] targetDataForType) {
        String[] animalType = DefaultData.ANIMAL_TYPE;
        Map<String, Integer> foodChoiceProbabilities = IntStream.range(0, targetDataForType.length)
                .filter(j -> targetDataForType[j] != 0)
                .boxed()
                .collect(Collectors.toMap(
                        j -> animalType[j],
                        j -> targetDataForType[j]
                ));
        return new PreferenceMenu(foodChoiceProbabilities);
    }

    public static Property makeProperty(String[] targetDataForType) {
        return new Property.PropertyBuilder()
                .setName(targetDataForType[0])
                .setIcon(targetDataForType[1])
                .setWeight(Double.parseDouble(targetDataForType[2]))
                .build();
    }

    public static LimitData makeLimitData(Double[] targetDataForType) {
        double maxWeight = targetDataForType[0];
        int maxSpeed = targetDataForType[1].intValue();
        double maxFoodFeed = targetDataForType[2];
        int maxCountUnit = targetDataForType[3].intValue();
        double dailyWeightLossPct = 0;
        return new LimitData(maxWeight, maxSpeed, maxFoodFeed, maxCountUnit, dailyWeightLossPct);
    }

    private static <T, R> void verifyArraysDataConsistency(T[] lengthType, R[][] lengthData) {
        if (lengthType.length != lengthData.length) {
            throw new AppException(FailMessagesApp.DIFFERENT_LENGTHS);
        }
    }
}
