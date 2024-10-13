package com.javarush.island.siberia2.config;

public class Constants {
    //SETTINGS
    public static final String YAML_PATH = "siberia2/settings.yaml";

    //TILES
    public static final String GRASS_PATH_X16 = "/siberia2/x16/ground/Grass.png";
    public static final String ROCKS_PATH_X16 = "/siberia2/x16/plants/Rocks.png";
    public static final String TREES_PATH_X16 = "/siberia2/x16/plants/Trees.png";
    public static final String WHEATS_PATH_X16 = "/siberia2/x16/plants/Wheatfield.png";
    public static final int FOREST_COUNT = 5;
    public static final int FOREST_SIZE = 6;
    public static final int FIELD_COUNT = 2;
    public static final int FIELD_SIZE = 4;

    //Ui
    public static final String WINDOW_NAME = "Island simulation";

    //SOUND
    public static final String MAIN_SOUND_PATH = "siberia2/sound/Music.wav";
    public static final int SLEEP_TIME_CHECK_THREAD = 100;

    //EXEPTION TEXTS
    public static final String NO_MUSIC_FILE = "Can't find file 'Music.wav'.";
    public static final String ERROR_LOAD_SETTING = "Error loading settings";
    public static final String RANDOM_UTIL_MIX_MAX = "min must be < max";
    public static final String RANDOM_UTIL_PERCENT = "percent must be from 0 to 100";

}