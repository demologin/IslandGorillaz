package com.javarush.island.popov.repo;

import com.javarush.island.popov.creators.PrototypeCreator;
import com.javarush.island.popov.units.Unit;

import java.util.Set;

public class Constants {
    public static  int ROWS = 2;
    public static  int COLS = 2;
    public static final int PERIOD = 1000;
    public static final int PERSENT_LOOSE_WEIGHT = 1;
    public static int DAYS_NUMBER = 10;
    public static final String CREATE_MAP_AND_FILL = "Lets create the Island and fill it by Animals and plant:";
    public static final String VERTICAL_RANGE = "Please enter the vertical map size";
    public static final String HORIZONTAL_RANGE = "Please enter the horizontal map size";
    public static final String LIFE_DAY_NUMBERS = "Please enter the life days number";

    public static final String SETTING_UNITS_YAML = "popov/another.yaml";

   public static final Set<Unit> PROTOTYPES = PrototypeCreator.createPrototypes();
    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
}
