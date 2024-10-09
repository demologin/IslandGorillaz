package com.javarush.island.siberia.entity.organism.animals.herbivores;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.animals.plants.Plant;
import com.javarush.island.siberia.entity.organism.animals.Animal;

import java.util.List;

public abstract class Herbivore extends Animal {
    public Herbivore(Location location) {
        super(location);
    }

    @Override
    public void eat() {
        List<Organism> organisms = this.getLocation().getOrganisms();
        for (Organism organism : organisms) {
            if (organism instanceof Plant && organism.isAlive()) {
                consume(organism);
                break;
            }
        }
    }

    private void consume(Organism plant) {
        plant.die();
        double foodAmount = Math.min(plant.getWeight(), this.getMaxFood() - this.getCurrentFood());
        this.setCurrentFood(this.getCurrentFood() + foodAmount);
        this.setSatiety(this.getCurrentFood() / this.getMaxFood());
    }

}