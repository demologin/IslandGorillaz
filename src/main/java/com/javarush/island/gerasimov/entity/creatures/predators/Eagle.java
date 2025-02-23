package com.javarush.island.gerasimov.entity.creatures.predators;

import com.javarush.island.gerasimov.utils.Config;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Config(filePath = "gerasimov/eagle.json")
public class Eagle extends Predator{
}
