package com.javarush.island.khmelov.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Life {
    int period = Default.PERIOD;
    int rows = Default.ROWS;
    int cols = Default.COLS;

    int percentAnimalSlim = Default.PERCENT_ANIMAL_SLIM;
    int percentPlantGrow = Default.PERCENT_PLANT_GROW;
}
