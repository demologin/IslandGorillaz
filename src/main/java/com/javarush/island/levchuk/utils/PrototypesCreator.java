package com.javarush.island.levchuk.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.entities.Entity;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PrototypesCreator {

    public List<Entity> loadPrototypes() {
        ConfigLoader configLoader = new ConfigLoader();
        List<Entity> prototypes = new ArrayList<>();
        Reflections reflections = new Reflections(Constants.DEFAULT_ENTITIES_FOLDER);
        Set<Class<? extends Entity>> entityClasses = reflections.getSubTypesOf(Entity.class);
        entityClasses.stream()
                .filter(e -> e.isAnnotationPresent(Config.class))
                .map(configLoader::getObject)
                .forEach(o -> prototypes.add((Entity) o));
        return prototypes;
    }

}
