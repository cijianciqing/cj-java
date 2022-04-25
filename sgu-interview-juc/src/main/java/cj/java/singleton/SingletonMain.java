package cj.java.singleton;


import java.util.concurrent.ExecutionException;

public class SingletonMain {


    public static void main(String[] args) throws ExecutionException, InterruptedException {



//        SingletonHungry shinstance = SingletonHungry.SHINSTANCE;
//        SingletonHungry shinstance2 = SingletonHungry.SHINSTANCE;
//        System.out.println(shinstance == shinstance2);


       /* Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"---> start");
            SingletonLazy singletonLazy = SingletonLazy.getSingletonLazy();
            System.out.println(singletonLazy);
        });

        Thread thread2= new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"---> start");
            SingletonLazy singletonLazy = SingletonLazy.getSingletonLazy();
            System.out.println(singletonLazy);
        });

        thread1.setName("t1");
        thread1.start();
        thread2.setName("t2");
        thread2.start();*/



    }

}
