package borisov.api;

import borisov.config.MyConfig;
import borisov.entity.Animals;
import borisov.entity.predatoranimal.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ConfigReader {
    private static ConfigReader instance;
    @Getter
    private static List<Map<String, Class<? extends Animals>>> yamlConfig;
    private final ObjectMapper mapper = new YAMLMapper(new YAMLFactory());

    private ConfigReader() {
        read();
    }

    private void read() {
        try (InputStream inputStream = Test.class.getClassLoader().getResourceAsStream(MyConfig.CONFIG_FILE_NAME);) {
            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + MyConfig.CONFIG_FILE_NAME);
            }
            yamlConfig = mapper.readValue(inputStream, List.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static ConfigReader getInstance() {
        if (instance == null){
            instance = new ConfigReader();
        }
        return instance;
    }
}