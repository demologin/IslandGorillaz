package shosafoev.simulation.thread;

import shosafoev.map.IslandMap;
import shosafoev.simulation.SettingUpTheMenu;
import shosafoev.simulation.thread.animalLifecycleTask.task.Eat;
import shosafoev.simulation.thread.animalLifecycleTask.task.HpDecrease;
import shosafoev.simulation.thread.animalLifecycleTask.task.Multiply;

public class StatisticsOfTheIsland implements Runnable{
    private boolean isTimeOver;
    private int babies;
    private int animalsEaten;
    private int animalsDiedByHungry;
    private int countAnimalsEnd;
    private int countPlants;
    private static int currentDay = 0;
    private final Multiply multiply;
    private final Eat eat;
    private final HpDecrease hpDecrease;

    public StatisticsOfTheIsland(Eat eat, HpDecrease hpDecrease, Multiply multiply) {
        this.eat = eat;
        this.hpDecrease = hpDecrease;
        this.multiply = multiply;
    }

    @Override
    public void run() {
        long timeNow = SettingUpTheMenu.getInstance().getTimeNow();
        isTimeOver = checkTime(timeNow);

        babies = multiply.getBabies();
        countAnimalsEnd = IslandMap.getInstance().getAllAnimals().size(); // number of animals on the island
        animalsEaten = eat.getAnimalsEaten(); // number of animals died
        animalsDiedByHungry = hpDecrease.getAnimalsDiedByHungry(); // number of animals died
        countPlants = IslandMap.getInstance().getAllPlants().size();

        printStats();

        if (isTimeOver) {
            SettingUpTheMenu.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
    }

    /**
     * Check if the specified simulation time has expired
     *
     * @param timeNow The current simulation time
     * @return isTimeOver true if the time has expired, otherwise false
     */
    private boolean checkTime(long timeNow) {
        return timeNow / 60 >= 5;
    }

    /**
     * Output simulation statistics
     */
    private void printStats() {
        if (isTimeOver) {
            System.out.println("VICTORY!!! YOU LASTED 5 MINUTES!");
            System.out.println("----------------------------------");
        } else {
            System.out.printf("--- DAY %d ---", currentDay);
            currentDay++;
            System.out.println();
        }
        System.out.println();
        System.out.println("STATISTICS ON THE ISLAND");
        System.out.println();

        System.out.print("Animals on the island: ");
        System.out.println(countAnimalsEnd);

        System.out.print("Animals died of starvation:");
        System.out.println(animalsDiedByHungry);

        System.out.print("Animals eaten: ");
        System.out.println(animalsEaten);

        System.out.print("Cubs were born: ");
        System.out.println(babies);

        System.out.print("Plants on the island: ");
        System.out.println(countPlants);



        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();
    }

    public static int getCurrentDay() {
        return currentDay;
    }
}
