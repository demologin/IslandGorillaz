package com.javarush.island.nikitin.application.config.configProviders;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.javarush.island.nikitin.application.exception.AppException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class ConfigLoader {

    public static <T> Map<String, T> fetchData(Class<?> clazzGeneric, URI resource) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        JavaType typeGeneric = typeFactory.constructParametricType(ConfigTemplateMap.class, clazzGeneric);
        try {
            ConfigTemplateMap<T> configTemplateMap = objectMapper.readValue(new File(resource), typeGeneric);
            return configTemplateMap.tempMap;

        } catch (IOException e) {
            throw new AppException(e);
        }
    }
}
