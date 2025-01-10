package com.javarush.island.stepanov.util;

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
        ObjectReader reader = mapper.readerForUpdating(object);
        URL resource = Setting.class.getClassLoader().getResource(ObjectFileDir);
        if (Objects.nonNull(resource)) {
            reader.readValue(resource.openStream());
        }
    }
}
