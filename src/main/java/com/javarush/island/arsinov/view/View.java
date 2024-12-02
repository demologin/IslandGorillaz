package com.javarush.island.arsinov.view;

import com.javarush.island.arsinov.model.animals.Animal;
import com.javarush.island.arsinov.model.Cell;
import com.javarush.island.arsinov.model.Island;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.stream.Collectors.joining;


public class View {

    public void displayIslandState(Island island) {
        System.out.println("Island Map: ");
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                Cell cell = island.getCell(i, j);
                System.out.print(formatCellContent(cell) + " ");
            }
            System.out.println();
        }
    }

    private String formatCellContent(Cell cell) {

        List<Character> symbols = new ArrayList<>();

        Set<Animal> animalCopy = new HashSet<>(cell.getAnimals());
        animalCopy.stream()
                .limit(4)
                .forEach(animal -> {
                    String name = animal.getClass().getSimpleName();
                    symbols.add(name.isEmpty() ? '?' : name.charAt(0));
                });


        if (!cell.getPlantSymbols().isEmpty()) {
            symbols.addAll(cell.getPlantSymbols());
        }

        String content = symbols.stream()
                .limit(4)
                .map(String::valueOf)
                .collect(joining());

        if (symbols.size() > 4) {
            content += "~";
        }

        return String.format("%-5s", content);
    }

    public void displayAnimalStatistics(Island island) {
        System.out.println("Animal Statistics: ");
        for (String animalType : Set.of("Wolf", "Boa", "Fox", "Grizzly", "Eagle", "Horse", "Deer", "Rabbit"
                , "Sheep", "Wild boar", "Buffalo", "Duck","Mouse","Goat")) {
            long count = new CopyOnWriteArrayList<>(island.getCells()).stream()
                    .flatMap(cell -> new CopyOnWriteArrayList<>(cell.getAnimals()).stream())
                    .filter(animal -> animal.getClass().getSimpleName().equals(animalType))
                    .count();
            System.out.printf("%s: %d, ", animalType, count);
        }
    }

    public void displayPlantStatics(Island island) {
        double totalPlantMass = island.getCells().stream()
                .mapToDouble(Cell::getPlantMass)
                .sum();
        System.out.println();
        System.out.printf("Total Plant Mass: %.2f%n", totalPlantMass);
        System.out.println();
    }
}
