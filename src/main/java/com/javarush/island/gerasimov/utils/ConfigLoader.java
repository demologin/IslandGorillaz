package com.javarush.island.gerasimov.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ConfigLoader {
    public <T> T getObject(Class<T> aClass) {
        if (!aClass.isAnnotationPresent(Config.class)) {
            throw new IllegalArgumentException();
        }
        File filePath = getConfigFilePath(aClass);
        return loadObject(filePath, aClass);
    }

    private File getConfigFilePath(Class<?> aClass) {
        Config config = aClass.getAnnotation(Config.class);
        URL resource = getClass().getClassLoader().getResource(config.filePath());
        if (resource != null) {
            return new File(resource.getFile());
        } else {
            throw new IllegalArgumentException("File not found: " + config.filePath());
        }
    }

    private <T> T loadObject(File filePath, Class<T> aClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        T object;
        try {
            object = objectMapper.readValue(filePath, aClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}