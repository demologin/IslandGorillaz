package com.javarush.island.nikitin.application.config.configProviders;

import com.javarush.island.nikitin.application.config.DefaultData;
import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.application.constants.ClazzList;
import com.javarush.island.nikitin.application.constants.FailMessagesApp;
import com.javarush.island.nikitin.application.constants.LogMessagesApp;
import com.javarush.island.nikitin.application.exception.AppException;
import com.javarush.island.nikitin.application.util.PathBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

public class ConfigController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);

    /**
     * Retrieves settings from the YAML file associated with the specified class.
     * If the file is not found, returns an empty Optional.
     *
     * @param clazzList - An object containing information about the class and its settings
     * @return - Optional containing settings if they were loaded successfully, otherwise an empty Optional
     */

    public static Optional<Settings> getSettings(ClazzList clazzList) {
        Class<?> clazz = clazzList.getClazz();
        Optional<URI> build = PathBuilder.getPathToResourceYaml(clazz);
        if (build.isEmpty()) {
            LOGGER.info(LogMessagesApp.CLASS_DEFAULT_DATA_LOADED, clazzList);
            return Optional.empty();
        } else {
            LOGGER.info(LogMessagesApp.LOADED_DATA_FROM_CONFIGURATION_FILE, clazzList);
        }
        Map<String, Settings> map = ConfigLoader.fetchData(clazz, build.get());
        return Optional.of(map.get("Settings"));
    }

    /**
     * Retrieves data as a map from the YAML file associated with the specified class.
     * If the file is not found, returns the default data.
     *
     * @param clazzList - An object containing information about the class and its settings
     * @param <T>       - Type of values stored in the returned map
     * @return - A map containing loaded data, where the keys are strings and the values are objects of type T
     */

    public static <T> Map<String, T> getMapData(ClazzList clazzList) {
        Class<?> clazz = clazzList.getClazz();
        Optional<URI> build = PathBuilder.getPathToResourceYaml(clazz);
        if (build.isEmpty()) {
            LOGGER.info(LogMessagesApp.CLASS_DEFAULT_DATA_LOADED, clazzList);
            return getDefaultData(clazzList);
        } else {
            LOGGER.info(LogMessagesApp.LOADED_DATA_FROM_CONFIGURATION_FILE, clazzList);
        }
        return ConfigLoader.fetchData(clazz, build.get());
    }

    /**
     * Gets the default data map for the specified class type.
     *
     * @param clazzList - an enumeration that specifies the type of data to be retrieved
     * @param <T>       - the data type to be returned in the map
     * @return - a data map corresponding to the specified class type
     * @see ClazzList
     */

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
