package com.javarush.island.khmelov.view;

@SuppressWarnings("unused")
public class Color {


    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String FILL_RED = "\u001B[41m";
    public static final String FILL_GREEN = "\u001B[42m";
    public static final String FILL_YELLOW = "\u001B[43m";
    public static final String FILL_BLUE = "\u001B[44m";
    public static final String FILL_PURPLE = "\u001B[45m";
    public static final String FILL_CYAN = "\u001B[46m";
    public static final String FILL_WHITE = "\u001B[47m";

    private static final String[] Scale = {
            FILL_GREEN, FILL_BLUE, FILL_YELLOW, FILL_PURPLE, FILL_WHITE,
            GREEN, BLUE, YELLOW, PURPLE, RED,
    };


    public static String getColor(int size, int maxCount) {
        if (size > maxCount) {
            return Color.FILL_RED;
        }
        int index = Scale.length - 1 - Scale.length * size / (maxCount + 1);
        return Scale[index];
    }
}
