package com.javarush.island.siberia;

import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.map.Island;
import com.javarush.island.siberia.simulation.Simulation;

/**
 * The ConsoleRunner class is the entry point for running the island simulation from the console.
 * It initializes the island, populates it with organisms, starts the simulation, and listens for
 * the user to stop the simulation by pressing Enter.
 */

public class ConsoleRunner {
    public static void main(String[] args) {

        // Load the settings instance
        Settings settings = Settings.getInstance();

        // Create the island
        Island island = new Island();

        // Populate the island with organisms
        island.populateIsland();

        // Create the simulation object
        Simulation simulation = new Simulation(island);

        // Start the simulation
        simulation.start();

        try {
            // Wait for the user to press Enter to stop the simulation
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        simulation.stop();
    }

}