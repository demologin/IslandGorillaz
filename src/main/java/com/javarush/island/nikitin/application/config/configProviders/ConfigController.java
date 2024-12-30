package com.javarush.island.nikitin.application.config.configProviders;

import com.javarush.island.nikitin.application.config.DefaultData;
import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.application.constants.ClazzList;
import com.javarush.island.nikitin.application.constants.FailMessagesApp;
import com.javarush.island.nikitin.application.exception.AppException;
import com.javarush.island.nikitin.application.util.PathBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

public class ConfigController {
    public static Optional<Settings> getSettings(ClazzList clazzList) {
        Class<?> clazz = clazzList.getClazz();
        Optional<URI> build = PathBuilder.getPathToResourceYaml(clazz);
        if (build.isEmpty()) {
            System.out.println("Settings fail load");
            return Optional.empty();
        }
        System.out.println("Settings try load");
        Map<String, Settings> map = ConfigLoader.fetchData(clazz, build.get());
        return Optional.of(map.get("Settings"));
    }

    public static <T> Map<String, T> getMapData(ClazzList clazzList) {
        Class<?> clazz = clazzList.getClazz();
        Optional<URI> build = PathBuilder.getPathToResourceYaml(clazz);
        if (build.isEmpty()) {
            System.out.println("data default");
            return getDefaultData(clazzList);
        }
        System.out.println("data load");
        return ConfigLoader.fetchData(clazz, build.get());
    }

    @SuppressWarnings("unchecked")
    private static <T> Map<String, T> getDefaultData(ClazzList clazzList) {
        return switch (clazzList) {
            case LIMIT_DATA -> (Map<String, T>) DefaultData.initLimitDataMap();
            case PROPERTY -> (Map<String, T>) DefaultData.initPropertyMap();
            case PREFERENCE_MENU -> (Map<String, T>) DefaultData.initPreferenceMenuMap();
            default -> throw new AppException(FailMessagesApp.UNSUPPORTED_CLASS);
        };
    }

    private ConfigController() {
    }
}
