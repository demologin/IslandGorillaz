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
    private double startOccupancyRate = 0.01d;
    @Getter
    private int rows = 100;
    @Getter
    private int columns = 20;
    @Getter
    private int startDate = 0;
    @Getter
    private double percentFillingLocation = 0.1d;
    @Getter
    private int periodLive = 200;

    private static final boolean SETTINGS_DEFAULT = true;


    public final Map<String, PreferenceMenu> preferenceMenuMap;
    private final Map<String, Property> propertyMap;
    private final Map<String, LimitData> limitDataMap;

    {
        preferenceMenuMap = ConfigController.getMapData(ClazzList.PREFERENCE_MENU);
        propertyMap = ConfigController.getMapData(ClazzList.PROPERTY);
        limitDataMap = ConfigController.getMapData(ClazzList.LIMIT_DATA);
        System.out.println("data config compelite");
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
            if(SETTINGS_DEFAULT){
                instance = new Settings();
                System.out.println("SETTINGS_DEFAULT true ");
            } else {
                Optional<Settings> settings = ConfigController.getSettings(ClazzList.SETTING);
                instance = settings.orElseGet(Settings::new);
                System.out.println("inst");
            }
        }
        return instance;
    }

    private Settings() {
    }
}
