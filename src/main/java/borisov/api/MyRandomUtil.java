package borisov.api;

import java.util.concurrent.ThreadLocalRandom;

public class MyRandomUtil {
    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
