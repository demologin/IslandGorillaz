package com.javarush.island.khmelov.view.javafx;

import com.javarush.island.khmelov.api.view.View;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.config.Window;
import com.javarush.island.khmelov.entity.Game;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizm.Organisms;
import com.javarush.island.khmelov.services.GameServiceProcessor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.stream.Collectors;

public class JavaFxView extends Application implements View {

    private static GameServiceProcessor gameWorker;

    private final int rows;
    private final int cols;
    private final Game game;

    private final int cellIconCount;
    private final int statWidth;
    private final int width;
    private final int height;

    private Label[][] labelCells;
    private Label statistics;

    public static void launchFxWindow(GameServiceProcessor gameWorkerService) {
        JavaFxView.gameWorker = gameWorkerService;
        launch();
        gameWorker.getGame().setFinished(true);
    }

    public JavaFxView() {
        game = gameWorker.getGame();
        rows = game.getGameMap().getRows();
        cols = game.getGameMap().getCols();

        Window window = Setting.get().window;
        statWidth = 200;
        cellIconCount = window.getCellIconCount();
        width = window.getWidth();
        height = window.getHeight();
    }

    @Override
    public void start(Stage primaryStage) {
        labelCells = new Label[rows][cols];
        statistics = createStatisticsBox();

        GridPane gameMapPane = createGameMapPane(labelCells);
        HBox hBox = new HBox(gameMapPane, statistics);

        Scene scene = new Scene(hBox, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
        game.setView(this); //view complete, set callback in game.
        Platform.runLater(gameWorker); //and run game
    }

    private Label createStatisticsBox() {
        Label label = new Label();
        label.setWrapText(true);
        label.setFont(Font.font(18));
        label.setMaxWidth(statWidth);
        label.setMinWidth(statWidth);
        return label;
    }

    private GridPane createGameMapPane(Label[][] viewMap) {
        GridPane gameMapPane = new GridPane();
        gameMapPane.setPrefHeight(height);
        gameMapPane.setPrefWidth(width - statWidth);


        for (int i = 0; i < cols; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100d / cols);
            col.setHgrow(Priority.ALWAYS);
            gameMapPane.getColumnConstraints().add(col);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100d / rows);
            row.setVgrow(Priority.NEVER);
            gameMapPane.getRowConstraints().add(row);
        }

        gameMapPane.setGridLinesVisible(true);
        for (int i = 0, mapLength = viewMap.length; i < mapLength; i++) {
            for (int j = 0; j < viewMap[i].length; j++) {
                Label label = new Label();
                label.setFont(Font.font(15));
                label.setWrapText(true);
                viewMap[i][j] = label;
                gameMapPane.add(viewMap[i][j], j, i);
            }
        }
        return gameMapPane;
    }

    @Override
    public void show() {
        showScale();
        showMap();
        showStatistics();
    }

    @Override
    public void showStatistics() {
        String text = game.getGameMap()
                .getStatistics()
                .entrySet()
                .stream()
                .map(e -> e.getKey().getIcon() + "(" + e.getKey().getName() + "): " + e.getValue())
                .collect(Collectors.joining("\n"));
        Platform.runLater(() -> statistics.setText(text));
    }

    @Override
    public void showScale() {
        //
    }

    @Override
    public void showMap() {
        Platform.runLater(this::fillViewMap);
    }

    private void fillViewMap() {
        Cell[][] cells = game.getGameMap().getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                String text = getIcons(cells[i][j]);
                labelCells[i][j].setText(text);
            }
        }
    }

    private String getIcons(Cell cell) {
        try {
            cell.getLock().lock();
            return cell.getResidents().values().stream()
                    .filter((list) -> !list.isEmpty())
                    .sorted((o1, o2) -> o2.size() - o1.size())
                    .limit(cellIconCount)
                    .map(Organisms::getIcon)
                    .collect(Collectors.joining());
        } finally {
            cell.getLock().unlock();
        }
    }
}
