package borisov.entity.predatoranimal;

import borisov.entity.map.GameMap;

import java.util.*;
import java.util.stream.Collectors;

public class Test {


    public static void main(String[] args) {
        Map<String, Set<String>> animalsInCell = new HashMap<>();
        animalsInCell.put("cell1", new HashSet<>(Set.of("lion", "tiger", "bear")));
        animalsInCell.put("cell2", new HashSet<>(Set.of("elephant", "zebra", "giraffe")));
        animalsInCell.put("cell3", new HashSet<>(Set.of("dog", "cat", "rabbit", "parrot", "hamster","cat1","cat2","cat3","cat4","cat5","cat6","cat7")));

        // Создаём поток из записей карты
        animalsInCell.entrySet().stream()
                // Ограничиваем каждый HashSet до 9 элементов (хотя у нас в примере обычно меньше)
                .map(entry -> {
                    Set<String> limitedSet = entry.getValue().stream()
                            .limit(9) // Ограничиваем количество элементов до 9
                            .collect(Collectors.toSet()); // Возвращаем ограниченное множество
                    return Map.entry(entry.getKey(), limitedSet); // Возвращаем пару ключ-ограниченное множество
                })
                .forEach(entry -> {
                    // Печатаем ключ и размер множества (если больше 9, будет ограничено)
                    System.out.println(entry.getKey() + " = " + entry.getValue().size());
                });
    }
    private static int plus(int a) {
        a = a+1;
            return a;
    }
}
