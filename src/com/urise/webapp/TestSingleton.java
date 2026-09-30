package com.urise.webapp;

public class TestSingleton {
    private static TestSingleton instance;
    public static TestSingleton getInstance() {
        if(instance ==null){
            new TestSingleton();
        }
        return instance;
    }
    private TestSingleton(){

    }

    public static void main(String[] args) {
        TestSingleton.getInstance().toString();
    }
}
