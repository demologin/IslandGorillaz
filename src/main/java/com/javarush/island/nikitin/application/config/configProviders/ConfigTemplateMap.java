package com.javarush.island.nikitin.application.config.configProviders;

import java.util.Map;

/**
 * class template for configuring a card from a YAML file
 * @param <T> - the type of data that this template will hold
 */

public class ConfigTemplateMap<T> {
    public Map<String, T> tempMap;
}
