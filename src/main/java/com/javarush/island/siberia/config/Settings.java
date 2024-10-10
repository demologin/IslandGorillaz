package com.javarush.island.siberia.config;

import lombok.Getter;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The Settings class manages the configuration of the simulation,
 * loading parameters from a YAML file. It provides access to island dimensions,
 * organism settings, probabilities for predator-prey interactions, and other key parameters.
 */

@Getter
public class Settings {
    private static Settings instance;
    private final Map<String, Object> config;

    /**
     * Private constructor that loads the configuration from the YAML file.
     * This method is called once when the singleton instance is created.
     */

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

    /**
     * Provides the singleton instance of the Settings class.
     *
     * @return The singleton instance of Settings.
     */

    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    /**
     * Retrieves the organism settings for a specific species from the configuration.
     *
     * @param species The species name (e.g., "Wolf", "Rabbit").
     * @return An OrganismSettings object containing the settings for the specified species.
     */

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

    /**
     * Retrieves the duration of a single simulation step from the configuration.
     *
     * @return The duration (in milliseconds) of a single simulation step.
     */

    public long getSimulationStepDuration() {
        Map<String, Object> island = (Map<String, Object>) config.get("island");
        return ((Number) island.get("simulationStepDuration")).longValue();
    }

    /**
     * Retrieves all organism settings from the configuration,
     * including both animals and plants.
     *
     * @return A map of organism settings, with species names as keys.
     */

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

    /**
     * Retrieves the predator-prey probabilities from the configuration,
     * which determine the chances of successful hunting.
     *
     * @return A map of predator species to their prey probabilities.
     */

    public Map<String, Map<String, Integer>> getProbabilities() {
        return (Map<String, Map<String, Integer>>) config.get("probabilities");
    }

    /**
     * Retrieves the prey probabilities for a specific predator species.
     *
     * @param predatorSpecies The name of the predator species.
     * @return A map of prey species and their corresponding probabilities of being hunted.
     */

    public Map<String, Integer> getPredatorProbabilities(String predatorSpecies) {
        Map<String, Map<String, Integer>> probabilities = getProbabilities();
        return probabilities.getOrDefault(predatorSpecies, Collections.emptyMap());
    }

    public double getHungerIncreaseRate() {
        Map<String, Object> island = (Map<String, Object>) config.get("island");
        return ((Number) island.get("hungerIncreaseRate")).doubleValue();
    }

}