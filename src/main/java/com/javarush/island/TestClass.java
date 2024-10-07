package com.javarush.island;

import java.net.URL;

public class TestClass {

    public static final String ORGANISM_DATA_TABLE = "/khmelov/setting.yaml";

    static {
        URL url =  TestClass.class.getResource(ORGANISM_DATA_TABLE);
        System.out.println("ok "+url); //ok not null
    }

    public static void main(String[] args) {
        //see ok
    }
}
