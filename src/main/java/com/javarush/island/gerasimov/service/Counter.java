package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.entity.creatures.Animal;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.entity.creatures.herbivores.*;
import com.javarush.island.gerasimov.entity.creatures.plants.Grass;
import com.javarush.island.gerasimov.entity.creatures.predators.*;
import com.javarush.island.gerasimov.entity.map.Cell;

public class Counter extends Thread {

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
    int sumOrganisms = 0;

    private void countingAllOrganism() {
        for (Cell[] row : EntityCreator.cells) {
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
        sumOrganisms = countGrass + countGoat + countDuck + countBear +
                countEagle + countFox + countSnake + countRabbit +
                countSheep + countHorse + countMouse + countDeer +
                countCaterpillar + countBuffalo + countBoar + countWolf;

        counter++;

        System.out.println("*** Неделя на острове: " + counter + " ***" + "\n" +
                "Всего на острове " + sumOrganisms + " организмов: " + (sumOrganisms - countGrass) + " животных и " + countGrass + " растений.\n" +
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
                "Растения: " + "\uD83C\uDF3F" + countGrass + "\n\n" +
                "-Растений выросло: " + Plant.reproduceCounter + "\n" +
                "-Растений съедено: " + Herbivore.eatCounter + "\n" +
                "-Растений умерло своей смертью: " + PlantsControlWeight.deadCounter + "\n" +
                "-Перемещений животных в другие локации: " + Animal.moveCounter + "\n" +
                "-Съедено животных: " + Predator.eatCounter + "\n" +
                "-Родилось животных: " + Animal.reproduceCounter + "\n" +
                "-Хищных животных умерло от голода: " + PredatorsDecrementWeight.deadCounter + "\n" +
                "-Травоядных животных умерло от голода: " + HerbivoresDecrementWeight.deadCounter + "\n" +
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
        Animal.moveCounter = 0;
        Animal.reproduceCounter = 0;
        Predator.eatCounter = 0;
        Plant.reproduceCounter = 0;
        PlantsControlWeight.deadCounter = 0;
        Herbivore.eatCounter = 0;
        PredatorsDecrementWeight.deadCounter = 0;
        HerbivoresDecrementWeight.deadCounter = 0;
    }

    @Override
    public void run() {
            countingAllOrganism();
    }
}
