package borisov.entity.predatoranimal;

import borisov.entity.map.GameMap;

public class Test {


    public static void main(String[] args) {
        System.out.println(test(6));
    }
    public static int test(int a){
        int i = 1;
        if (a==0){return i;}
        return i+test(a-1);
    }
}
