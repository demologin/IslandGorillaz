package com.javarush.island.kozlov.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.fasterxml.jackson.dataformat.yaml.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ProbabilityTable {

    private static final Map<Class<? extends Animal>, Map<Class<? extends Animal>, Integer>> table = new ConcurrentHashMap<>();

    static {
        loadPredationData();
    }

    private static void loadPredationData() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try (InputStream inputStream = ProbabilityTable.class.getClassLoader().getResourceAsStream("percentages.yaml")) {
            if (inputStream == null) {
                throw new RuntimeException("Не удается найти .yaml файл");
            }

            ProbabilityData data = mapper.readValue(inputStream, ProbabilityData.class);

            for (Map.Entry<String, Map<String, Integer>> predatorEntry : data.getPercentages().entrySet()) {
                String predatorName = predatorEntry.getKey();
                Map<String, Integer> preyData = predatorEntry.getValue();

                Class<? extends Animal> predatorClass = getAnimalClass(predatorName);
                if (predatorClass == null) {
                    continue;
                }

                Map<Class<? extends Animal>, Integer> preyMap = new ConcurrentHashMap<>();
                for (Map.Entry<String, Integer> preyEntry : preyData.entrySet()) {
                    String preyName = preyEntry.getKey();
                    Integer chance = preyEntry.getValue();
                    Class<? extends Animal> preyClass = getAnimalClass(preyName);
                    if (preyClass != null) {
                        preyMap.put(preyClass, chance);
                    }
                }

                table.put(predatorClass, preyMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<? extends Animal> getAnimalClass(String className) {
        try {
            return (Class<? extends Animal>) Class.forName("animals." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getAnimalsChance(Class<? extends Animal> predator, Class<? extends Animal> prey) {
        return table.getOrDefault(predator, new ConcurrentHashMap<>()).getOrDefault(prey, 0);
    }

    public static boolean tryToEat(Class<? extends Animal> predator, Class<? extends Animal> prey) {
        int chance = getAnimalsChance(predator, prey);

        int randomValue = ThreadLocalRandom.current().nextInt(100);
        return randomValue < chance;
    }
}
