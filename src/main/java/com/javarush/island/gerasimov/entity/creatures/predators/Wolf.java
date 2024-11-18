package com.javarush.island.gerasimov.entity.creatures.predators;

import com.javarush.island.gerasimov.utils.Config;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@Config(filePath = "gerasimov/wolf.json")
public class Wolf extends Predator {
}
