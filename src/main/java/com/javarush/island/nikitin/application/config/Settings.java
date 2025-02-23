package com.javarush.island.nikitin.application.config;

import com.javarush.island.nikitin.application.config.configProviders.ConfigController;
import com.javarush.island.nikitin.application.constants.ClazzList;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;

@Getter
public class Settings {
    private final int rows = 100;
    private final int columns = 20;
    private final int startDate = 0;
    private final double percentFillingLocation = 1d;
    private final int periodLife = 1000;
    private final int rowsPrint = 6;
    private final int columnsPrint = 2;
    private final int countBiotaPrint = 18;
    private final int stepWarmUpCacheNavigator = 3;

    @Getter(AccessLevel.NONE)
    private static Settings instance;
    @Getter(AccessLevel.NONE)
    private final Map<String, PreferenceMenu> preferenceMenuMap;
    @Getter(AccessLevel.NONE)
    private final Map<String, Property> propertyMap;
    @Getter(AccessLevel.NONE)
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
