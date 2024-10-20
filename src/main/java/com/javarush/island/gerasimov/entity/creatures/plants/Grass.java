package com.javarush.island.gerasimov.entity.creatures.plants;

import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.utils.Config;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Config(filePath = "gerasimov/grass.json")
public class Grass extends Plant {
}

