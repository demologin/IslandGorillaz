package shosafoev.simulation.startMenu;

import shosafoev.map.IslandMap;
import shosafoev.simulation.SettingUpTheMenu;

import java.util.Scanner;

public class Parameters {
    /**
     * Method for changing simulation parameters
     */
    public void changeParameters() {
        changeIslandSize();
        int countPredators = changePredatorsSize();
        int countHerbivores = changeHerbivoresSize();
        int countPlants = changePlantsSize();

        if (countHerbivores == 0) {
            countHerbivores = SettingUpTheMenu.getInstance().getCountHerbivores();
        }
        if (countPredators == 0) {
            countPredators = SettingUpTheMenu.getInstance().getCountPredators();
        }
        if (countPlants == 0) {
            countPlants = SettingUpTheMenu.getInstance().getCountPlants();
        }

        SettingUpTheMenu.getInstance().createIslandModel(countHerbivores, countPredators, countPlants);
    }

    /**
     * Method for changing the size of the island
     */
    private void changeIslandSize() {
        System.out.println("Do you want to change the game settings? Or play by default?");
        System.out.println("Do you want to change the size of the island (10x4)?");
        System.out.println("Do you want to change the number of predators (25)?");
        System.out.println("Do you want to change the number of herbivores (30)?");
        System.out.println("Do you want to change the number of plants (10)?");

        System.out.println("1- Change the parameters");
        System.out.println("2- Play by default");
        System.out.println("Enter the number to change the parameters of the map ");
        int answer = takeInt(1, 2);
        if (answer == 1) {
            System.out.println("Enter the desired map size!");
            System.out.println("Number of lines:");
            int rows = takeInt(1, 500);
            System.out.println("Number of columns:");
            int columns = takeInt(1, 500);
            IslandMap.getInstance().initializeLocations(rows, columns);
        } else {
            IslandMap.getInstance().initializeLocations();
        }
    }

    /**
     * Method for changing the number of predators
     *
     * @return countPredators Number of predators
     */
    private int changePredatorsSize() {
        System.out.println("Enter the number to change the Predator parameters ");
        int countPredators = 0;
        int answer = takeInt(1, 2);
        if (answer == 1) {
            System.out.println("Enter the desired number of Predators from 5 to 500!");
            System.out.println("Number of predators:");
            countPredators = takeInt(5, 500);
        }
        return countPredators;
    }

    /**
     * Method for changing the number of herbivores
     *
     * @return counterbivores The number of herbivores
     */
    private int changeHerbivoresSize() {
        System.out.println("Enter a number to change the parameters of Herbivores ");
        int countHerbivores = 0;
        int answer = takeInt(1, 2);

        if (answer == 1) {
            System.out.println("Enter the desired number of herbivores from 10 to 500!");
            System.out.println("Number of herbivores:");
            countHerbivores = takeInt(10, 500);
        }
        return countHerbivores;
    }

    /**
     * Method for changing the number of plants
     *
     * @return countPlants Number of plants
     */
    private int changePlantsSize() {
        System.out.println("enter the number to change the Plant parameters");
        int countPlants = 0;
        int answer = takeInt(1, 2);

        if (answer == 1) {
            System.out.println("Enter the desired number of plants from 1 to 500!");
            System.out.println("Number of plants:");
            countPlants = takeInt(1, 500);
        }
        return countPlants;
    }

    /**
     * Method for reading an integer from the keyboard in a given range
     *
     * @param lowNum Lower bound of the range
     * @param highNum Upper limit of the range
     * @return number The read number
     */
    public int takeInt(int lowNum, int highNum) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number >= lowNum && number <= highNum) {
                    return number;
                } else {
                    System.out.println("A mistake! The entered number is not in the specified range. Try again:");
                }
            } else {
                scanner.next();
                System.out.println("A mistake! An incorrect value has been entered. Try again:");
            }
        }
    }
}
