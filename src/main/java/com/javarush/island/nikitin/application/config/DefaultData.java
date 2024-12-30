package com.javarush.island.nikitin.application.config;

import com.javarush.island.nikitin.application.config.configProviders.DefaultDataAssembler;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;

import java.util.Map;

public class DefaultData {
    public static Map<String, PreferenceMenu> PREFERENCE_MENU_MAP;
    public static Map<String, Property> PROPERTY_MAP;
    public static Map<String, LimitData> LIMIT_DATA_MAP;

    public static final String[] ANIMAL_TYPE = {"Wolf", "Boa", "Fox", "Bear", "Eagle", "Horse", "Deer",
            "Rabbit", "Mouse", "Goat", "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar", "Grass"};
    public static final Integer[][] PROBABILITY_OF_EATING = {
            {0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0, 0},
            {0, 0, 15, 0, 0, 0, 0, 20, 40, 0, 0, 0, 0, 10, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 70, 90, 0, 0, 0, 0, 60, 40, 0},
            {0, 80, 0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0, 0},
            {0, 0, 10, 0, 0, 0, 0, 90, 90, 0, 0, 0, 0, 80, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    /**
     * String name;
     * String icon;
     * double weight;
     */

    public static final String[][] PROPERTY_BIOTA = {
            {"wolf", "\uD83D\uDC3A", "50"},
            {"boa", "\ud83d\udc0d", "15"},
            {"fox", "\ud83e\udd8a", "8"},
            {"bear", "\ud83d\udc3b", "500"},
            {"eagle", "\ud83e\udd85", "6"},
            {"horse", "\ud83d\udc0e", "400"},
            {"deer", "\ud83e\udd8c", "300"},
            {"rabbit", "\uD83D\uDC07", "2"},
            {"mouse", "\ud83d\udc01", "0.05"},
            {"goat", "\ud83d\udc10", "60"},
            {"sheep", "\ud83d\udc11", "70"},
            {"boar", "\ud83d\udc17", "400"},
            {"buffalo", "\ud83d\udc03", "700"},
            {"duck", "\ud83e\udd86", "1"},
            {"caterpillar", "\ud83d\udc1b", "0.01"},
            {"plant", "\uD83C\uDF3F", "1"}
    };

    /**
     * double maxWeight,
     * int maxSpeed,
     * double maxFoodFeed,
     * int maxCountUnit,
     * double dailyWeightLossPct
     */
    public static final Double[][] LIMITS = {
            {50d, 3d, 8d, 30d},
            {15d, 1d, 3d, 30d},
            {8d, 2d, 2d, 30d},
            {500d, 2d, 80d, 5d},
            {6d, 3d, 1d, 20d},
            {400d, 4d, 60d, 20d},
            {300d, 4d, 50d, 20d},
            {2d, 2d, 0.45d, 150d},
            {0.05d, 1d, 0.01d, 500d},
            {60d, 3d, 10d, 140d},
            {70d, 3d, 15d, 140d},
            {400d, 2d, 50d, 50d},
            {700d, 3d, 100d, 10d},
            {1d, 4d, 0.15, 200d},
            {0.01d, 0d, 0d, 1000d},
            {1d, 0d, 0d, 200d}
    };

    public static Map<String, PreferenceMenu> initPreferenceMenuMap() {
        if (PREFERENCE_MENU_MAP == null) {
            PREFERENCE_MENU_MAP = DefaultDataAssembler.collectData(ANIMAL_TYPE, PROBABILITY_OF_EATING, DefaultDataAssembler::makePreferenceMenu);
        }
        return PREFERENCE_MENU_MAP;
    }

    public static Map<String, Property> initPropertyMap() {
        if (PROPERTY_MAP == null) {
            PROPERTY_MAP = DefaultDataAssembler.collectData(ANIMAL_TYPE, PROPERTY_BIOTA, DefaultDataAssembler::makeProperty);
        }
        return PROPERTY_MAP;
    }

    public static Map<String, LimitData> initLimitDataMap() {
        if (LIMIT_DATA_MAP == null) {
            LIMIT_DATA_MAP = DefaultDataAssembler.collectData(ANIMAL_TYPE, LIMITS, DefaultDataAssembler::makeLimitData);
        }
        return LIMIT_DATA_MAP;
    }
}
