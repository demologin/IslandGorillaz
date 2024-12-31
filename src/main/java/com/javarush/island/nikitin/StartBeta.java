package com.javarush.island.nikitin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.nikitin.application.config.DefaultData;
import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.application.config.configProviders.DefaultDataAssembler;
import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class StartBeta<T> {

    @JsonProperty("propsMaps")
    public Map<String, T> propsMaps;

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));

        String[][] PROPERTY_BIOTA = {
                {"wolf", "\uD83D\uDC3A", "50"},
                {"boa", "\ud83d\udc0d", "15"},
                {"fox", "\ud83e\udd8a", "8"},
                {"bear", "\ud83d\udc3b", "500"},
                {"eagle", "\ud83e\udd85", "6"},
                {"horse", "\ud83d\udc0e", "400"},
                {"deer", "\ud83e\udd8c", "300"},
                {"rabbit", "\uD83D\uDC07", "2"},
                {"mouse", "\ud83d\udc01", "0.05"},
                {"goat", "\ud83d\udc10", "60"},
                {"sheep", "\ud83d\udc11", "70"},
                {"boar", "\ud83d\udc17", "400"},
                {"buffalo", "\ud83d\udc03", "700"},
                {"duck", "\ud83e\udd86", "1"},
                {"caterpillar", "\ud83d\udc1b", "0.01"},
                {"plant", "\uD83C\uDF3F", "1"}
        };
        System.out.println(Arrays.deepToString(PROPERTY_BIOTA));
    }

    private static void staticViewSwing() {
        String[] array = {"Элемент 1", "Элемент 2", "Элемент 3", "Элемент 4"};

        // Создание окна
        JFrame frame = new JFrame("Вкладки: Массив и Статистика");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Создание JTabbedPane для вкладок
        JTabbedPane tabbedPane = new JTabbedPane();

        // Вкладка для отображения массива
        JPanel arrayPanel = new JPanel();
        JList<String> list = new JList<>(array);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        arrayPanel.setLayout(new BorderLayout());
        arrayPanel.add(scrollPane, BorderLayout.CENTER);
        tabbedPane.addTab("Массив", arrayPanel);

        // Вкладка для отображения статистики
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3, 1)); // Используем GridLayout для упрощения расположения

        // Подсчет статистики
        int elementCount = array.length;
        String elementType = "String"; // Тип элементов массива

        // Создание меток для статистики
        JLabel countLabel = new JLabel("Количество элементов: " + elementCount);
        JLabel typeLabel = new JLabel("Тип элементов: " + elementType);
        JLabel infoLabel = new JLabel("Массив: " + String.join(", ", array));

        // Добавление меток на панель статистики
        statsPanel.add(countLabel);
        statsPanel.add(typeLabel);
        statsPanel.add(infoLabel);
        tabbedPane.addTab("Статистика", statsPanel);

        // Добавление вкладок в окно
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Установка видимости окна
        frame.setVisible(true);

    }

    private void checkAssembler() {
        Map<String, PreferenceMenu> stringPreferenceMapMap = DefaultDataAssembler.collectData(DefaultData.ANIMAL_TYPE, DefaultData.PROBABILITY_OF_EATING, DefaultDataAssembler::makePreferenceMenu);
        printPreferenceMap(stringPreferenceMapMap);

        Map<String, Property> stringPropsMap = DefaultDataAssembler.collectData(DefaultData.ANIMAL_TYPE, DefaultData.PROPERTY_BIOTA, DefaultDataAssembler::makeProperty);
        printProps(stringPropsMap);

        Map<String, LimitData> stringLimitDataMap = DefaultDataAssembler.collectData(DefaultData.ANIMAL_TYPE, DefaultData.LIMITS, DefaultDataAssembler::makeLimitData);
        printLimits(stringLimitDataMap);
    }

    private static void printMap(Map<String, ?> map) {
        Set<? extends Map.Entry<String, ?>> entries = map.entrySet();
        for (Map.Entry<String, ?> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void printMapSettingClass(Map<String, Settings> map) {
        Set<? extends Map.Entry<String, Settings>> entries = map.entrySet();
        for (Map.Entry<String, Settings> entry : entries) {
            System.out.println(entry.getKey() + ": \n\t" + entry.getValue() + "\n\t");
        }
    }


    private static void printLimits(Map<String, LimitData> map) {
        Set<? extends Map.Entry<String, LimitData>> entries = map.entrySet();
        for (Map.Entry<String, LimitData> entry : entries) {
            System.out.println(entry.getKey() + ": " + "\t");
            System.out.println("\t" + entry.getValue().maxWeight());
            System.out.println("\t" + entry.getValue().maxCountUnit());
            System.out.println("\t" + entry.getValue().maxFoodFeed());
            System.out.println("\t" + entry.getValue().maxWeight());


        }
    }

    private static void printProps(Map<String, Property> map) {
        Set<? extends Map.Entry<String, Property>> entries = map.entrySet();
        for (Map.Entry<String, Property> entry : entries) {
            System.out.println(entry.getKey() + ": " + "\n\t" + entry.getValue());
            //System.out.println("\t" + entry.getValue().getName() + "\t" + entry.getValue().getIcon() +"\t" + entry.getValue().getWeight());
        }
    }

    private static void printPreferenceMap(Map<String, PreferenceMenu> map) {
        Set<? extends Map.Entry<String, PreferenceMenu>> entries = map.entrySet();
        for (Map.Entry<String, PreferenceMenu> entry : entries) {
            Map<String, Integer> stringIntegerMap = entry.getValue().foodChoiceProbabilities();

            System.out.println(entry.getKey());
            Set<Map.Entry<String, Integer>> entries1 = stringIntegerMap.entrySet();
            for (Map.Entry<String, Integer> stringIntegerEntry : entries1) {
                System.out.println("\t" + stringIntegerEntry.getKey() + ": " + stringIntegerEntry.getValue());

            }
        }
    }

    private static void writeSettingsToTarget(Map<String, Property> s) throws Exception {
        ObjectMapper objectMapper = new YAMLMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        URI path = Biota.class.getResource("/nikitin/temp.yaml").toURI();

        String path1 = Path.of(path).toString();
        System.out.println(s.get("Wolf"));
        String sssss = objectMapper.writeValueAsString(s);
        System.out.println(sssss);
        objectMapper.writeValue(new File(path1), s);

    }

    private static void writePropsMapToTarget(Map<String, Property> map) throws Exception {
        ObjectMapper objectMapper = new YAMLMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        URI path = Biota.class.getResource("/nikitin/1props.yaml").toURI();

        String path1 = Path.of(path).toString();
        objectMapper.writeValue(new File(path1), map);
    }

    private static void readPropsMapToTarget(Class<?> clazz) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        URI path = Objects.requireNonNull(Biota.class.getResource("/nikitin/1props.yaml")).toURI();

        String path1 = Path.of(path).toString();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(StartBeta.class, clazz);
        StartBeta props = objectMapper.readValue(new File(path1), type);
        System.out.println(props.propsMaps);

    }
}