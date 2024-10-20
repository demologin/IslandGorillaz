package com.javarush.island.ivanov.utils;


import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;


import java.io.IOException;

public class ConfigLoader {

    public <T> T getObject(Class<T> aClass){
        if (!aClass.isAnnotationPresent(Config.class)){
            throw new IllegalArgumentException();
        }
        File filePath = getConfigFilePath(aClass);
        return loadObject(filePath, aClass);
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

    public File getConfigFilePath(Class<?> aClass){
        Config config = aClass.getAnnotation(Config.class);
        return new File(config.filePath());
    }

}
