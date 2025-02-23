package com.javarush.island.khmelov.config;

import com.javarush.island.khmelov.api.annotation.OrganismLimitData;
import com.javarush.island.khmelov.entity.organizm.Limit;
import com.javarush.island.khmelov.entity.organizm.Organism;
import com.javarush.island.khmelov.exception.GameException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EntityScanner {

    private EntityScanner() {
    }

    public static Organism[] createPrototypes(Class<?>[] TYPES) {
        Organism[] organisms = new Organism[TYPES.length];
        int index = 0;
        for (Class<?> type : TYPES) {
            if (type.isAnnotationPresent(OrganismLimitData.class)) {
                OrganismLimitData typeData = type.getAnnotation(OrganismLimitData.class);
                String name = typeData.name();
                String icon = typeData.icon();
                int flockSize = typeData.flockSize();
                Limit limit = new Limit(
                        typeData.maxCountInCell() / flockSize,
                        typeData.maxWeight() * flockSize,
                        typeData.maxSpeed(),
                        typeData.maxFood() * flockSize,
                        flockSize
                );

                organisms[index++] = generatePrototype(type, name, icon, limit);
            }
        }
        return organisms;
    }

    private static Organism generatePrototype(Class<?> type, String name, String icon, Limit limit) {
        try {
            Constructor<?> constructor = type.getConstructor(String.class, String.class, Limit.class);
            return (Organism) constructor.newInstance(name, icon, limit);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new GameException("not found Entity constructor", e);
        }
    }
}
