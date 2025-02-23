package com.javarush.island.ivanov.utils;

import com.javarush.island.ivanov.constants.Constants;
import com.javarush.island.ivanov.entity.organism.Organism;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class PrototypesCreator {

    public List<Organism> loadPrototypes() {
        ConfigLoader configLoader = new ConfigLoader();
        List<Organism> organisms = new ArrayList<>();
        Reflections reflections = new Reflections(Constants.DEFAULT_ENTITIES_FOLDER);
        Set<Class<? extends Organism>> organismsClasses = reflections.getSubTypesOf(Organism.class);
        organismsClasses.stream()
                .filter(e -> e.isAnnotationPresent(Config.class))
                .map(configLoader::getObject)
                .forEach(o -> organisms.add((Organism) o));

        return organisms;
    }
}
