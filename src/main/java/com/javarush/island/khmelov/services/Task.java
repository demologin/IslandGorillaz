package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizm.Organism;
import com.javarush.island.khmelov.entity.organizm.animals.Animal;
import lombok.Getter;

@Getter
public class Task {

    private final Organism organism;
    private final Cell cell;

    public Task(Organism organism, Cell cell) {
        this.organism = organism;
        this.cell = cell;
    }

    public void doTask() {
        if (organism instanceof Animal animal) {
            if (animal.eat(cell)) {
                animal.spawn(cell);
            }
            animal.move(cell);
        } else {
            organism.spawn(cell);
        }
    }

}
