package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.utils.Config;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Config(filePath = "gerasimov/sheep.json")
public class Sheep extends Herbivore{
}
