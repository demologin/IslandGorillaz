package borisov.entity.predatoranimal;

import borisov.api.AnimalsFactory;
import borisov.entity.Animals;
import borisov.entity.map.GameMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Test {


    public static void main(String[] args) throws JsonProcessingException {
        GameMap map = new GameMap(2, 3);
        Wolf wolf = new Wolf();
        ObjectMapper mapper = new YAMLMapper(new YAMLFactory());
        List<Map<String, Class<? extends Animals>>> yamlData;
        try (InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.yaml");) {
            if (inputStream == null) {
                System.out.println("Ресурс не найден!");
                return;
            }
            yamlData = mapper.readValue(inputStream, List.class);



            System.out.println(yamlData);
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        String yamlBook = mapper.writeValueAsString(wolf);
//        System.out.println(yamlBook);
//
//        ObjectMapper mapperR = new YAMLMapper();
//        Wolf book = mapper.readValue(yamlBook, Wolf.class);
//        System.out.println(book.getWeight() +" "+ book.getMoveSpeed());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
