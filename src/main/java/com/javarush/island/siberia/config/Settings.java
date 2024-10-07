package com.javarush.island.siberia.config;

import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Settings {
    private static Settings instance;
    private final Map<String, Object> config;

    private Settings() {
        Yaml yaml = new Yaml();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("settings.yaml")) {
            config = yaml.load(in);
        } catch (Exception e) {
            throw new RuntimeException("error loading settings", e);
        }
    }

    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public OrganismSettings getOrganismSettings(String species) {
        Map<String, Object> organisms = (Map<String, Object>) config.get("animals");
        if (organisms == null) {
            organisms = (Map<String, Object>) config.get("plants");
        }
        Map<String, Object> speciesSettings = (Map<String, Object>) organisms.get(species);
        if (speciesSettings == null) {
            throw new RuntimeException("cant find config for " + species);
        }
        return new OrganismSettings(speciesSettings);
    }

    public long getSimulationStepDuration() {
        Map<String, Object> island = (Map<String, Object>) config.get("island");
        return ((Number) island.get("simulationStepDuration")).longValue();
    }

    public int getIslandWidth() {
        Map<String, Object> islandSettings = (Map<String, Object>) config.get("island");
        return (int) islandSettings.get("width");
    }

    public int getIslandHeight() {
        Map<String, Object> islandSettings = (Map<String, Object>) config.get("island");
        return (int) islandSettings.get("height");
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

}