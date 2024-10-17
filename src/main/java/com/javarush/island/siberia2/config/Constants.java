package com.javarush.island.siberia2.config;

public class Constants {
    //SETTINGS
    public static final String YAML_PATH = "siberia2/settings.yaml";

    //START PARAMETERS
    public static final double EVERYSTEP_DECREASE_HUNGER = 0.01; //use 0.1 to 10% per turn, 0.01 is for long living
    public static final int STARTING_CELLS_WITH_FOOD = 300;
    public static final int MAX_ANIMAL_TO_START = 30;

    //TILES
    public static final String GRASS_PATH_X16 = "/siberia2/x16/ground/Grass.png";
    public static final String ROCKS_PATH_X16 = "/siberia2/x16/plants/Rocks.png";
    public static final String TREES_PATH_X16 = "/siberia2/x16/plants/Trees.png";
    public static final String WHEATS_PATH_X16 = "/siberia2/x16/plants/Wheatfield.png";
    public static final int FOREST_COUNT = 5;
    public static final int FOREST_SIZE = 6;
    public static final int FIELD_COUNT = 3;
    public static final int FIELD_SIZE = 4;
    public static final int RIVER_COUNT = 2;
    public static final int ROAD_COUNT = 2;
    public static final int LAKE_COUNT = 1;

    //Ui
    public static final String WINDOW_NAME = "Island simulation";

    //SOUND
    public static final String MAIN_SOUND_PATH = "siberia2/sound/Music.wav";
    public static final int SLEEP_TIME_CHECK_THREAD = 100;

    //EXCEPTION TEXTS
    public static final String NO_MUSIC_FILE = "Can't find file 'Music.wav'.";
    public static final String ERROR_LOAD_SETTING = "Error loading settings";
    public static final String ISLAND_OUT_IF_ISLAND = "Coordinates out of island bounds";
    public static final String FACTORY_UNKNOWN_ORGANISM = "Unknown organism: ";

}