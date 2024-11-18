package com.javarush.island.khmelov.entity.organizm.plants;

import com.javarush.island.khmelov.api.annotation.TypeData;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizm.Limit;
import com.javarush.island.khmelov.entity.organizm.Organism;
import com.javarush.island.khmelov.entity.organizm.Organisms;
import com.javarush.island.khmelov.util.Rnd;

@TypeData(name = "Трава", icon = "\uD83E\uDEB4", maxWeight = 1, maxCountInCell = 200, flockSize = 20, maxSpeed = 0, maxFood = 0)
public class Grass extends Organism {
    public Grass(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

    @Override
    public boolean spawn(Cell cell) {
        this.safeChangeWeight(cell, Setting.get().life.getPercentPlantGrow());
        boolean born = false;
        for (int i = 0; i < 6; i++) {
            Cell neighborCell = cell.getNextCell(Rnd.random(0, 2));
            born |= safePlantPropagation(neighborCell);
        }
        return born;
    }

    private boolean safePlantPropagation(Cell cell) {
        Limit limit = getLimit();
        synchronized (cell.monitor()){
            Organisms plants = cell.getResidents().get(getType());
            if (plants.size() < limit.getMaxCountInCell() &&
                getWeight() > limit.getMaxWeight() / 2
            ) {
                Organism newPlant = Organism.clone(this);
                double childWeight = getWeight() / 10;
                newPlant.setWeight(childWeight);
                return plants.add(newPlant);
            }
        }
        return false;
    }

    @Override
    public boolean eat(Cell currentCell) {
        return false;
    }

    @Override
    public boolean move(Cell startCell) {
        return false;
    }
}
