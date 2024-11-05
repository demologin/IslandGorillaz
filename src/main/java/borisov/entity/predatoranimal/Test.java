package borisov.entity.predatoranimal;

import borisov.entity.map.GameMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {


    public static void main(String[] args) {
       int now =1;
       int temp =0;
       for (int i = 0; i < 4; i++) {
           temp = plus(now);
           now = temp;
       }
       System.out.println(temp);

    }
    private static int plus(int a) {
        a = a+1;
            return a;
    }
}
