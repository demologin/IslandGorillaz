package borisov.api;

import java.util.concurrent.ThreadLocalRandom;

import static java.util.concurrent.ThreadLocalRandom.current;

public class MyRandomUtil {
    public static int random(int min, int max) {
        return current().nextInt(min, max);
    }
    public static boolean randomPercent (int percent) {return ThreadLocalRandom.current().nextInt(100) < percent;}
}
