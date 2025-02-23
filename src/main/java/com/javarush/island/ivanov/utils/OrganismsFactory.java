package com.javarush.island.ivanov.utils;

import com.javarush.island.ivanov.entity.organism.Organism;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganismsFactory {
    @Getter
    private final static Map<Class<? extends Organism>, Organism> organisms = new HashMap<>();

    public void registerOrganism(List<Organism> entity) {
        for (Organism organism : entity) {
            organisms.put(organism.getClass(), organism);
        }
    }

    public static Organism getOrganism(Class<? extends Organism> type) {
        return copyEntity(organisms.get(type));
    }

    public static Class<? extends Organism> getOrganismClass(String className) {
        return organisms.entrySet().stream()
                .filter(entry -> entry.getValue().getName().equals(className))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static <T> T copyEntity(T entity) {
        Class<?> entityClass = entity.getClass();
        T newEntity;
        try {
            newEntity = (T) entityClass.getConstructor().newInstance();
            while (entityClass != null) {
                Field[] fields = entityClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(newEntity, field.get(entity));
                }
                entityClass = entityClass.getSuperclass();
            }
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException();
        }
        return newEntity;
    }
}
