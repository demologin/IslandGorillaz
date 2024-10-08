package com.javarush.island.siberia;

import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.map.Island;
import com.javarush.island.siberia.simulation.Simulation;

public class ConsoleRunner {
    public static void main(String[] args) {

        Settings settings = Settings.getInstance();

        Island island = new Island();

        island.populateIsland();

        Simulation simulation = new Simulation(island);

        simulation.start();

        try {
            System.out.println("Press Enter to stop simulation...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        simulation.stop();
    }

}