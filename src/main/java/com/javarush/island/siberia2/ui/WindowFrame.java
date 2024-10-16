package com.javarush.island.siberia2.ui;

import com.javarush.island.siberia2.config.ConfigLoader;
import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.entity.map.Island;
import com.javarush.island.siberia2.entity.map.MapData;
import com.javarush.island.siberia2.simulation.Simulation;
import com.javarush.island.siberia2.ui.tileFactory.*;
import lombok.Getter;

import javax.swing.*;

public class WindowFrame extends JFrame implements Runnable {

    Settings settings = ConfigLoader.loadSettings();
    int islandWidth = settings.getIslandSettings().getWidth();
    int islandHeight = settings.getIslandSettings().getHeight();
    int windowWidth = settings.getWindowSettings().getWidth();
    int windowHeight = settings.getWindowSettings().getHeight();
    int tileSize = settings.getWindowSettings().getTileSize();
    int scale = settings.getWindowSettings().getScale();
    @Getter
    private TilePanel tilePanel;

    public WindowFrame() {
        initUI();
    }

    private void initUI() {
        setTitle(Constants.WINDOW_NAME);
        setSize(windowWidth, windowHeight);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MapData mapData = new MapData(islandWidth, islandHeight);
        Island island = new Island(islandWidth, islandHeight, mapData);

        tilePanel = getTilePanel(mapData, island);
        add(tilePanel);

        Simulation simulation = new Simulation(settings);
        new Thread(simulation).start();

        Sound sound = new Sound();
        sound.setFile(0);
        sound.play();
        sound.loop();
    }

    private TilePanel getTilePanel(MapData mapData, Island island) {
        initResource initResource = getInitResource();

        TileFiller tileFiller = new TileFiller(initResource.groundAssetToTile(), tileSize, scale, mapData);
        ObjectLayer objectLayer = new ObjectLayer(initResource.rocksAssetToTile(),
                initResource.treesAssetToTile(),
                initResource.wheatAssetToTile(),
                tileSize,
                scale,
                island.getMapData());
        AnimalLayer animalLayer = new AnimalLayer(island, settings, tileSize, scale);

        return new TilePanel(tileFiller, objectLayer, animalLayer);
    }

    private record initResource(TileManager groundAssetToTile,
                                TileManager rocksAssetToTile,
                                TileManager treesAssetToTile,
                                TileManager wheatAssetToTile) {
    }

    private initResource getInitResource() {
        TileManager groundAssetToTile = new TileManager(Constants.GRASS_PATH_X16, tileSize);
        TileManager rocksAssetToTile = new TileManager(Constants.ROCKS_PATH_X16, tileSize);
        TileManager treesAssetToTile = new TileManager(Constants.TREES_PATH_X16, tileSize);
        TileManager wheatAssetToTile = new TileManager(Constants.WHEATS_PATH_X16, tileSize);
        return new initResource(groundAssetToTile, rocksAssetToTile, treesAssetToTile, wheatAssetToTile);
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            tilePanel.repaint();
        });
    }

}