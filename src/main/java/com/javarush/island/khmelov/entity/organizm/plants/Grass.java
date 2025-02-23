package com.javarush.island.khmelov.entity.organizm.plants;

import com.javarush.island.khmelov.api.annotation.OrganismLimitData;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizm.Limit;
import com.javarush.island.khmelov.entity.organizm.Organism;
import com.javarush.island.khmelov.entity.organizm.Organisms;
import com.javarush.island.khmelov.util.Rnd;

@OrganismLimitData(name = "Трава", icon = "\uD83E\uDEB4", maxWeight = 1, maxCountInCell = 200, flockSize = 20, maxSpeed = 0, maxFood = 0)
public class Grass extends Organism {
    public Grass(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

    @Override
    public boolean spawn(Cell cell) {
        //logic
        return false;
    }

}
