package com.javarush.island.siberia.config;

import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Settings {
    private static Settings instance;
    private final Map<String, Object> config;

    private Settings() {
        Yaml yaml = new Yaml();
        InputStream in = null;
        try {
            in = getClass().getClassLoader().getResourceAsStream("siberia/settings.yaml");
            if (in == null) {
                throw new RuntimeException("File settings.yaml didn't find");
            }
            config = yaml.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Error load configurations", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    throw new RuntimeException("Error while close");
                }
            }
        }
    }

    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public OrganismSettings getOrganismSettings(String species) {
        Map<String, Object> speciesSettings = null;

        Map<String, Object> animals = (Map<String, Object>) config.get("animals");
        if (animals != null) {
            speciesSettings = (Map<String, Object>) animals.get(species);
        }

        if (speciesSettings == null) {
            Map<String, Object> plants = (Map<String, Object>) config.get("plants");
            if (plants != null) {
                speciesSettings = (Map<String, Object>) plants.get(species);
            }
        }

        if (speciesSettings == null) {
            throw new RuntimeException("Can't find settings for " + species);
        }

        return new OrganismSettings(speciesSettings);

    }

    public long getSimulationStepDuration() {
        Map<String, Object> island = (Map<String, Object>) config.get("island");
        return ((Number) island.get("simulationStepDuration")).longValue();
    }

    public Map<String, OrganismSettings> getAllOrganismsSettings() {
        Map<String, OrganismSettings> allSettings = new HashMap<>();
        Map<String, Object> animals = (Map<String, Object>) config.get("animals");
        Map<String, Object> plants = (Map<String, Object>) config.get("plants");

        if (animals != null) {
            for (String species : animals.keySet()) {
                allSettings.put(species, getOrganismSettings(species));
            }
        }
        if (plants != null) {
            for (String species : plants.keySet()) {
                allSettings.put(species, getOrganismSettings(species));
            }
        }
        return allSettings;
    }

    public Map<String, Map<String, Integer>> getProbabilities() {
        return (Map<String, Map<String, Integer>>) config.get("probabilities");
    }

    public Map<String, Integer> getPredatorProbabilities(String predatorSpecies) {
        Map<String, Map<String, Integer>> probabilities = getProbabilities();
        return probabilities.getOrDefault(predatorSpecies, Collections.emptyMap());
    }

    public double getHungerIncreaseRate() {
        Map<String, Object> island = (Map<String, Object>) config.get("island");
        return ((Number) island.get("hungerIncreaseRate")).doubleValue();
    }

}