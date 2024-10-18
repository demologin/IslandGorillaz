package com.javarush.island.siberia2.config;

import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.io.InputStream;

public class ConfigLoader {
    public static Settings loadSettings() {
        Yaml yaml = new Yaml();
        try (InputStream in = ConfigLoader.class.getClassLoader().getResourceAsStream(Constants.YAML_PATH)) {
            return yaml.loadAs(in, Settings.class);
        } catch (IOException e) {
            throw new RuntimeException(Constants.ERROR_LOAD_SETTING, e);
        }
    }
}