package com.javarush.island.nikitin.application.config;

import com.javarush.island.nikitin.application.config.configProviders.ConfigController;
import com.javarush.island.nikitin.application.constants.ClazzList;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;

public class Settings {

    private static Settings instance;
    @Getter
    private int rows = 100;
    @Getter
    private int columns = 20;
    @Getter
    private int startDate = 0;
    @Getter
    private double percentFillingLocation = 1d;
    @Getter
    private int periodLife = 1000;
    @Getter
    private int rowsPrint = 6;
    @Getter
    private int columnsPrint = 2;
    @Getter
    private int countBiotaPrint = 18;
    @Getter
    private int stepWarmUpCacheNavigator = 3;


    private final Map<String, PreferenceMenu> preferenceMenuMap;
    private final Map<String, Property> propertyMap;
    private final Map<String, LimitData> limitDataMap;

    {
        preferenceMenuMap = ConfigController.getMapData(ClazzList.PREFERENCE_MENU);
        propertyMap = ConfigController.getMapData(ClazzList.PROPERTY);
        limitDataMap = ConfigController.getMapData(ClazzList.LIMIT_DATA);
    }


    public PreferenceMenu getPreferenceMenuByName(String nameCommunity) {
        return preferenceMenuMap.get(nameCommunity);
    }

    public Property getPropertyByName(String nameCommunity) {
        return propertyMap.get(nameCommunity);
    }

    public LimitData getLimitDataByName(String nameCommunity) {
        return limitDataMap.get(nameCommunity);
    }

    public static Settings newInstance() {
        if (instance == null) {
            Optional<Settings> settingsLoader = ConfigController.getSettings(ClazzList.SETTING);
            instance = settingsLoader.orElseGet(Settings::new);
        }
        return instance;
    }

    private Settings() {
    }
}
