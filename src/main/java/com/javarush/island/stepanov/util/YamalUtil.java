package com.javarush.island.stepanov.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.javarush.island.stepanov.config.Setting;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.Objects;

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
}
