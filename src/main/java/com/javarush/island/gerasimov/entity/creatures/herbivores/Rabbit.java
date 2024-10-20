package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.utils.Config;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Config(filePath = "gerasimov/rabbit.json")
public class Rabbit extends Herbivore{
}
