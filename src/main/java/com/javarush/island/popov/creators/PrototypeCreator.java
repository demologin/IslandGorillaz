package com.javarush.island.popov.creators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.javarush.island.popov.units.Unit;
import com.javarush.island.popov.units.flora.Flora;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.javarush.island.popov.repo.Constants.SETTING_UNITS_YAML;

public class PrototypeCreator {

    public static Set<Unit> createPrototypes() {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Set<Unit> unitsSet;
        try (InputStream inputStream = PrototypeCreator.class.getClassLoader().getResourceAsStream(SETTING_UNITS_YAML)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found!");
            }
            List<Unit> animals = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, Unit.class));
            unitsSet = animals.stream().collect(Collectors.toSet());
            for (Unit animal : unitsSet) {
                double maxUnitWeight = animal.getMaxUnitWeight();
                double weight = ThreadLocalRandom.current().nextDouble(maxUnitWeight / 2, maxUnitWeight);
                animal.setWeight(weight);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return unitsSet;
    }

}

