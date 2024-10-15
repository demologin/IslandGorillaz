package com.javarush.island.siberia2.config;

public class Constants {
    //SETTINGS
    public static final String YAML_PATH = "siberia2/settings.yaml";

    //TILES
    public static final String GRASS_PATH_X16 = "/siberia2/x16/ground/Grass.png";
    //public static final String ROCKS_PATH_X16 = "/siberia2/x16/plants/Rocks.png"; //TODO uncomment
    public static final String ROCKS_PATH_X16 = "/siberia2/x16/ground/buildings/RedResources.png"; //TODO for visual test only. delete this!
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
    public static final String RANDOM_UTIL_MIX_MAX = "min must be < max";
    public static final String RANDOM_UTIL_PERCENT = "percent must be from 0 to 100";
    public static final String ISLAND_OUT_IF_ISLAND = "Coordinates out of island bounds";
    public static final String FACTORY_UNKNOWN_ANIMAL = "Unknown animal: ";

    //ANIMALS PNG PATHS
    public static final String PNG_WOLF_PATH = "/siberia2/x16/animals/Wolf.png";
    public static final String PNG_BOA_PATH = "/siberia2/x16/animals/Boa.png";
    public static final String PNG_FOX_PATH = "/siberia2/x16/animals/Fox.png";
    public static final String PNG_BEAR_PATH = "/siberia2/x16/animals/Bear.png";
    public static final String PNG_EAGLE_PATH = "/siberia2/x16/animals/Eagle.png";
    public static final String PNG_HORSE_PATH = "/siberia2/x16/animals/Horse.png";
    public static final String PNG_DEER_PATH = "/siberia2/x16/animals/Deer.png";
    public static final String PNG_RABBIT_PATH = "/siberia2/x16/animals/Rabbit.png";
    public static final String PNG_MOUSE_PATH = "/siberia2/x16/animals/Mouse.png";
    public static final String PNG_GOAT_PATH = "/siberia2/x16/animals/Goat.png";
    public static final String PNG_SHEEP_PATH = "/siberia2/x16/animals/Sheep.png";
    public static final String PNG_BOAR_PATH = "/siberia2/x16/animals/Boar.png";
    public static final String PNG_BUFFALO_PATH = "/siberia2/x16/animals/Buffalo.png";
    public static final String PNG_DUCK_PATH = "/siberia2/x16/animals/Duck.png";
    public static final String PNG_CATERPILLAR_PATH = "/siberia2/x16/animals/Caterpillar.png";
}