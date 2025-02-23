package com.javarush.island.myasnikov.config;

import com.javarush.island.myasnikov.utility.Limit;
import com.javarush.island.myasnikov.utility.OrganismTypes;
import lombok.Getter;

import java.util.HashMap;

public class Params {
    public final static int SIDE_SIZE = 10;

    public static final int CELL_VIEW_LIMIT = 3;

    public static final int CELL_WIDTH = 9;

    public static int PLAY_DAYS = 500;

    public static final int[][] MOVE_TO_CELLS = {
            {-1, 0},  // top
            {1, 0},   // bottom
            {0, -1},  // left
            {0, 1},   // right
            {-1, -1}, // top-left
            {-1, 1},  // top-right
            {1, -1},  // bottom-left
            {1, 1}    // bottom-right
    };

    @Getter
    static final int[][] eatProbablyTable = {
            {0, 0,  0,  0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0,  0},
            {0, 0,  15, 0, 0, 0,  0,  20, 40, 0,  0,  0,  0,  10, 0,  0},
            {0, 0,  0,  0, 0, 0,  0,  70, 90, 0,  0,  0,  0,  60, 40, 0},
            {0, 80, 0 , 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0,  0},
            {0, 0 , 10, 0, 0, 0,  0,  90, 90, 0,  0,  0,  0,  80, 0,  0},
            {0, 0 , 0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  100},
            {0, 0 , 0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  100},
            {0, 0 , 0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  100},
            {0, 0 , 0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  90, 100},
            {0, 0 , 0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  100},
            {0, 0 , 0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  100},
            {0, 0 , 0,  0, 0, 0,  0,  0,  50, 0,  0,  0,  0,  0,  90, 100},
            {0, 0 , 0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  100},
            {0, 0 , 0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  90, 100},
            {0, 0 , 0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  100},
            {0, 0 , 0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
    };

    public static final String WOLF_ICON =        "🐺";
    public static final String SNAKE_ICON =       "🐍";
    public static final String FOX_ICON =         "🦊";
    public static final String BEAR_ICON =        "🐻";
    public static final String EAGLE_ICON =       "🦅";
    public static final String HORSE_ICON =       "🐎";
    public static final String DEER_ICON =        "🦌";
    public static final String RABBIT_ICON =      "🐇";
    public static final String MOUSE_ICON =       "🐁";
    public static final String GOAT_ICON =        "🐐";
    public static final String SHEEP_ICON =       "🐑";
    public static final String BOAR_ICON =        "🐗";
    public static final String BUFFALO_ICON =     "🦬";
    public static final String DUCK_ICON =        "🦬";
    public static final String CATERPILLAR_ICON = "🐛";
    public static final String PLANT_ICON =       "🌿";

    public static final Limit WOLF_LIMIT =        new Limit (50, 30,3);
    public static final Limit SNAKE_LIMIT =       new Limit (15,30,1);
    public static final Limit FOX_LIMIT =         new Limit (8,30,2);
    public static final Limit BEAR_LIMIT =        new Limit (500,5,2);
    public static final Limit EAGLE_LIMIT =       new Limit (6,20,3);
    public static final Limit HORSE_LIMIT =       new Limit (400, 20, 4);
    public static final Limit DEER_LIMIT =        new Limit (300,20, 4);
    public static final Limit RABBIT_LIMIT =      new Limit (2,150,2);
    public static final Limit MOUSE_LIMIT =       new Limit (0.05, 500, 1);
    public static final Limit GOAT_LIMIT =        new Limit (60,140,3);
    public static final Limit SHEEP_LIMIT =       new Limit (70,140,3);
    public static final Limit BOAR_LIMIT =        new Limit (400,50,2);
    public static final Limit BUFFALO_LIMIT =     new Limit (700,10,3);
    public static final Limit DUCK_LIMIT =        new Limit (1,200,4);
    public static final Limit CATERPILLAR_LIMIT = new Limit (0.01,1000,0);
    public static final Limit PLANT_LIMIT =       new Limit (1,200,0);


    @Getter
    public static HashMap<OrganismTypes, Integer> animalsSpawnLimit = new HashMap<>() {{
        put(OrganismTypes.Wolf, 50);
        put(OrganismTypes.Snake, 500);
        put(OrganismTypes.Fox, 50);
        put(OrganismTypes.Bear, 5);
        put(OrganismTypes.Eagle, 100);
        put(OrganismTypes.Horse, 50);
        put(OrganismTypes.Deer, 70);
        put(OrganismTypes.Rabbit, 1000);
        put(OrganismTypes.Mouse, 10000);
        put(OrganismTypes.Goat, 60);
        put(OrganismTypes.Sheep, 120);
        put(OrganismTypes.Boar, 400);
        put(OrganismTypes.Buffalo, 20);
        put(OrganismTypes.Duck, 400);
        put(OrganismTypes.Caterpillar, 10000);
        put(OrganismTypes.Plant, 10000);
    }};
}
