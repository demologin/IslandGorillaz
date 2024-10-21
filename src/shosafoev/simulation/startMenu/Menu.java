package shosafoev.simulation.startMenu;


import shosafoev.map.IslandMap;
import shosafoev.simulation.SettingUpTheMenu;

public class Menu {
    private final Parameters parameters;


    public Menu() {
        parameters = new Parameters();
    }

    public void startGame() {


        parameters.changeParameters();

        IslandMap.getInstance().initializeLocations();
        SettingUpTheMenu.getInstance().createIslandModel();


}
}

