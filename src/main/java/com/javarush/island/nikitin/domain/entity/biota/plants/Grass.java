package com.javarush.island.nikitin.domain.entity.biota.plants;

import com.javarush.island.nikitin.domain.api.InjectProps;
import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.biota.Props;
import com.javarush.island.nikitin.domain.entity.map.Location;

@GameUnit
@InjectProps(name = "grass", icon = "\uD83C\uDF3F", maxWeight = 1d, maxCountUnit = 200)
public class Grass extends Biota {
    public Grass(Props props) {
        super(props);
    }

    @Override
    public boolean eat(Location habitat) {
        return true;
    }

    @Override
    public void migrate(Location habitat) {
    }
}
