package com.javarush.island.levchuk.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.island.levchuk.entitys.Entity;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PrototypesCreator {

    public List<Entity> loadPrototypes() {
        List<Entity> prototypes = new ArrayList<>();
        Reflections reflections = new Reflections("com.javarush.island.levchuk.entitys.organisms");
        Set<Class<? extends Entity>> entityClasses = reflections.getSubTypesOf(Entity.class);
        entityClasses.forEach(entityClass -> prototypes.add(setConfiguration(entityClass)));
        return prototypes;
    }

    public <T> T setConfiguration(Class<T> clazz) {
        if (!clazz.isAnnotationPresent(Config.class)) {
            throw new IllegalArgumentException();
        }
        T object;
        Config config = clazz.getAnnotation(Config.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            object = objectMapper.readValue(config.filePath(), clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}
