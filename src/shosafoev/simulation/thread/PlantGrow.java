package shosafoev.simulation.thread;

import shosafoev.simulation.SettingUpTheMenu;

public class PlantGrow implements Runnable {
    public void run() {
        int countPlants = 20;
        if (SettingUpTheMenu.getInstance().getTimeNow() >= 2) {
            SettingUpTheMenu.getInstance().placePlants(countPlants / 2);
        } else {
            SettingUpTheMenu.getInstance().placePlants(countPlants);
        }
    }
}
