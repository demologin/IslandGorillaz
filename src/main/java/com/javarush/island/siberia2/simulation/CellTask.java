package com.javarush.island.siberia2.simulation;

import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;

import java.util.concurrent.RecursiveAction;

public class CellTask extends RecursiveAction {
    private static final int THRESHOLD = Constants.THRESHOLD_DIVIDER;
    private final int startY;
    private final int endY;
    private final Island island;
    private final SimulationStepHandler simulationStepHandler;

    public CellTask(int startY, int endY, Island island, SimulationStepHandler simulationStepHandler) {
        this.startY = startY;
        this.endY = endY;
        this.island = island;
        this.simulationStepHandler = simulationStepHandler;
    }

    @Override
    protected void compute() {
        if (endY - startY <= THRESHOLD) {
            for (int y = startY; y < endY; y++) {
                for (int x = 0; x < island.getWidth(); x++) {
                    Cell cell = island.getCell(x, y);
                    simulationStepHandler.processCell(cell);
                }
            }
        } else {
            int middle = (startY + endY) / 2;
            CellTask leftTask = new CellTask(startY, middle, island, simulationStepHandler);
            CellTask rightTask = new CellTask(middle, endY, island, simulationStepHandler);

            invokeAll(leftTask, rightTask);
        }
    }

}