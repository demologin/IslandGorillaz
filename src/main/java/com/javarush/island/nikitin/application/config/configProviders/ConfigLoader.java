package com.javarush.island.nikitin.application.config.configProviders;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.javarush.island.nikitin.application.constants.ClazzList;
import com.javarush.island.nikitin.application.exception.AppException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class ConfigLoader {

    /**
     * Loads configuration data from the specified YAML resource and converts it into a map.
     *
     * @param clazzGeneric - The class that will be used to type the values in the returned map.
     * @param resource     - The resource URI that points to the YAML file from which the data will be loaded
     * @param <T>          - Type of values stored in the returned map
     * @return - A map containing loaded configuration data,where the keys are strings and the values are objects of type
     * @throws AppException - If an error occurred while reading a file or converting data
     */

    public static <T> Map<String, T> fetchData(Class<?> clazzGeneric, URI resource) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        Class<?> clazz = ClazzList.CONFIG_TEMPLATE_MAP.getClazz();
        JavaType typeGeneric = typeFactory.constructParametricType(clazz, clazzGeneric);
        try {
            ConfigTemplateMap<T> configTemplateMap = objectMapper.readValue(new File(resource), typeGeneric);
            return configTemplateMap.tempMap;

        } catch (IOException e) {
            throw new AppException(e);
        }
    }
}
