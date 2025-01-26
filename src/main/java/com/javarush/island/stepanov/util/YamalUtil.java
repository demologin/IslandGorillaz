package com.javarush.island.stepanov.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.exception.AppException;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.javarush.island.stepanov.constants.Constants.*;

public class YamalUtil {
    @SneakyThrows
    public static void loadFromYaml(Object object, String ObjectFileDir) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY) // Видимость полей
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE) // Отключаем геттеры
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE) // Отключаем сеттеры
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE)); // Отключаем конструкторы
        ObjectReader reader = mapper.readerForUpdating(object);
        URL resource = Setting.class.getClassLoader().getResource(ObjectFileDir);
        if (Objects.nonNull(resource)) {
            reader.readValue(resource.openStream());
        }
    }

    public static List<String> getYamlFilesFromDirectory(String resourcePath) {
        List<String> yamlFiles = new ArrayList<>();
        try {
            URL directoryURL = YamalUtil.class.getClassLoader().getResource(resourcePath);
            if (directoryURL == null) {
                throw new AppException(YAMAL_DIRECTORY_NOT_FOUND_EXCEPTION + resourcePath);
            }
            File directory = new File(directoryURL.toURI());
            if (directory.isDirectory()) {
                File[] files = directory.listFiles((dir, name) -> name.endsWith(".yaml") || name.endsWith(".yml"));
                if (files != null) {
                    for (File file : files) {
                        yamlFiles.add(resourcePath + "/" + file.getName());
                    }
                }
            } else {
                throw new AppException(YAMAL_PATH_NOT_DIRECTORY_EXCEPTION + resourcePath);
            }
        } catch (Exception e) {
            throw new AppException(YAMAL_EXCEPTION, e);
        }
        return yamlFiles;
    }
}
