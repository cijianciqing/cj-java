package cj.java.juc.singleton;

public class SingletonHungry {
    public static final SingletonHungry SHINSTANCE = new SingletonHungry();
    private SingletonHungry(){

    }
}
