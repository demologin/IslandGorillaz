package com.javarush.island.kozlov.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.javarush.island.kozlov.entities.animals.Animal;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ProbabilityTable {
    private static final Map<Class<? extends Animal>, Map<Class<? extends Animal>, Integer>> table = new ConcurrentHashMap<>();

    // Загрузка данных о вероятностях из YAML
    static {
        loadProbabilities();
    }

    private static void loadProbabilities() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try (InputStream inputStream = ProbabilityTable.class.getClassLoader().getResourceAsStream("percentages.yaml")) {
            if (inputStream == null) {
                throw new RuntimeException("Cannot find percentages.yaml");
            }

            ProbabilityData data = mapper.readValue(inputStream, ProbabilityData.class);
            for (Map.Entry<String, Map<String, Integer>> entry : data.getPercentages().entrySet()) {
                Class<? extends Animal> predator = getClassByName(entry.getKey());
                if (predator != null) {
                    Map<Class<? extends Animal>, Integer> preyMap = new ConcurrentHashMap<>();
                    for (Map.Entry<String, Integer> preyEntry : entry.getValue().entrySet()) {
                        Class<? extends Animal> prey = getClassByName(preyEntry.getKey());
                        if (prey != null) {
                            preyMap.put(prey, preyEntry.getValue());
                        }
                    }
                    table.put(predator, preyMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<? extends Animal> getClassByName(String className) {
        try {
            return (Class<? extends Animal>) Class.forName("your.package." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Метод для получения шанса поедания
    public static int getProbability(Class<? extends Animal> predator, Class<? extends Animal> prey) {
        return table.getOrDefault(predator, new ConcurrentHashMap<>()).getOrDefault(prey, 0);
    }

    // Проверка, съест ли хищник добычу
    public static boolean tryToEat(Class<? extends Animal> predator, Class<? extends Animal> prey) {
        int chance = getProbability(predator, prey);
        int randomValue = ThreadLocalRandom.current().nextInt(100);
        return randomValue < chance;  // Вернёт true, если шанс сработал
    }
}
