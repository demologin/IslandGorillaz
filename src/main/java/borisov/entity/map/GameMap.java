package borisov.entity.map;

import java.util.Arrays;

public class GameMap {
    private final Cell[][] map;
    private final int height;
    private final int width;

    public GameMap(int width, int height) {
        this.height = height;
        this.width = width;
        this.map = new Cell[height][width];
        for (int i = 0; i < height; i++) {
           for (int j = 0; j < width; j++) {
               map[i][j] = new Cell();
           }
        }




    }

    @Override
    public String toString() {
        Cell[] a;
        Cell[] b;
        StringBuilder s = new StringBuilder();
        s.append("GameMap{\n");
        for (Cell[] row : map) {
            a = row;
            s.append(Arrays.toString(a)).append("\n");
        }
        return s.toString();

    }

    public Cell[][] getMap() {
        return map;
    }
    public Cell getCell(int x, int y) {
        return map[y][x];
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }



}
