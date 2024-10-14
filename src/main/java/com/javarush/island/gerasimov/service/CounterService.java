package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.entity.creatures.Animal;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.entity.creatures.grass.Grass;
import com.javarush.island.gerasimov.entity.creatures.herbivores.*;
import com.javarush.island.gerasimov.entity.creatures.predators.*;
import com.javarush.island.gerasimov.entity.map.Cell;

public class CounterService extends Thread {
    private int counter = 0;
    private int countGrass = 0;
    private int countWolf = 0;
    private int countHorse = 0;
    private int countBuffalo = 0;
    private int countBear = 0;
    private int countEagle = 0;
    private int countFox = 0;
    private int countSnake = 0;
    private int countBoar = 0;
    private int countCaterpillar = 0;
    private int countDeer = 0;
    private int countDuck = 0;
    private int countGoat = 0;
    private int countMouse = 0;
    private int countRabbit = 0;
    private int countSheep = 0;

    private void countingAllOrganism() {
        for (Cell[] row : EntityService.cells) {
            for (Cell cell : row) {
                for (Organism organism : cell.getOrganisms()) {
                    if (organism instanceof Grass) {
                        countGrass++;
                    } else if (organism instanceof Wolf) {
                        countWolf++;
                    } else if (organism instanceof Horse) {
                        countHorse++;
                    } else if (organism instanceof Buffalo) {
                        countBuffalo++;
                    } else if (organism instanceof Bear) {
                        countBear++;
                    } else if (organism instanceof Eagle) {
                        countEagle++;
                    } else if (organism instanceof Fox) {
                        countFox++;
                    } else if (organism instanceof Snake) {
                        countSnake++;
                    } else if (organism instanceof Boar) {
                        countBoar++;
                    } else if (organism instanceof Caterpillar) {
                        countCaterpillar++;
                    } else if (organism instanceof Deer) {
                        countDeer++;
                    } else if (organism instanceof Duck) {
                        countDuck++;
                    } else if (organism instanceof Goat) {
                        countGoat++;
                    } else if (organism instanceof Mouse) {
                        countMouse++;
                    } else if (organism instanceof Rabbit) {
                        countRabbit++;
                    } else if (organism instanceof Sheep) {
                        countSheep++;
                    }
                }
            }
        }
        int sumAll = countGrass + countGoat + countDuck + countBear +
                countEagle + countFox + countSnake + countRabbit +
                countSheep + countHorse + countMouse + countDeer +
                countCaterpillar + countBuffalo + countBoar + countWolf;
        counter++;
        System.out.println("День на острове: " + counter + "\n" +
                "Всего на острове " + sumAll + " организмов: " + (sumAll - countGrass) + " животных и " + countGrass + " растений.\n" +
                "Животные: " +
                "\uD83D\uDC17" + "=" + countBoar +
                " \uD83D\uDC3A" + "=" + countWolf +
                " \uD83D\uDC0E" + "=" + countHorse +
                " \uD83D\uDC03" + "=" + countBuffalo +
                " \uD83D\uDC3B" + "=" + countBear +
                " \uD83E\uDD85" + "=" + countEagle +
                " \uD83E\uDD8A" + "=" + countFox +
                " \uD83D\uDC0D" + "=" + countSnake +
                " \uD83D\uDC1B" + "=" + countCaterpillar +
                " \uD83E\uDD8C" + "=" + countDeer +
                " \uD83E\uDD86" + "=" + countDuck +
                " \uD83D\uDC10" + "=" + countGoat +
                " \uD83D\uDC2D" + "=" + countMouse +
                " \uD83D\uDC07" + "=" + countRabbit +
                " \uD83D\uDC11" + "=" + countSheep + "\n" +
                "Растения: " + "🌿" + countGrass + "\n\n" +
                "Растений выросло: " + Plant.reproduceCounter + "\n" +
                "Растений съедено: " + Herbivore.eatCounter + "\n" +
                "Животных першло в другие локации: " + Animal.moveCounter + "\n" +
                "Животных родилось: " + Animal.reproduceCounter + "\n" +
                "Съедено животных: " + Predator.eatCounter + "\n" +
                "Животных умерло от голода: " + AnimalDecrementWeightService.deadCounter + "\n" +
                "____________________________________________________________________________________________________");
        countGrass = 0;
        countWolf = 0;
        countHorse = 0;
        countBuffalo = 0;
        countBear = 0;
        countEagle = 0;
        countFox = 0;
        countSnake = 0;
        countBoar = 0;
        countCaterpillar = 0;
        countDeer = 0;
        countDuck = 0;
        countGoat = 0;
        countMouse = 0;
        countRabbit = 0;
        countSheep = 0;
        sumAll = 0;
        Animal.moveCounter = 0;
        Animal.reproduceCounter = 0;
        Predator.eatCounter = 0;
        Plant.reproduceCounter = 0;
        Herbivore.eatCounter = 0;
        AnimalDecrementWeightService.deadCounter = 0;
    }

    @Override
    public void run() {
        countingAllOrganism();
    }
}
