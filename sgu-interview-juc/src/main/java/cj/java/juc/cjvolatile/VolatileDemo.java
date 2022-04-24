package cj.java.juc.cjvolatile;

import java.util.concurrent.atomic.AtomicInteger;


class MyData {
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

}

/**
 * 验证volatile的可见性a
 * 1.当number未被volatile修饰时，new Thread将number值改为60，但main线程并不知道，会一直在循环中出不来
 * 2.当number使用volatile修饰，new Thread改变number值后，会通知main线程主内存的值已被修改，结束任务。体现了可见性
 */
public class VolatileDemo {
    public static void main(String[] args) {
        seeByVolatile();
    }

    //验证可见性的方法
    public static void seeByVolatile() {
        MyData myData = new MyData();
        //第一个线程
        new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " come in");
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myData.addTo60();
                System.out.println(Thread.currentThread().getName() + " update number to " + myData.number);
            }
        }.start();

        //第二个线程 main
        while (myData.number == 0) {

        }

        System.out.println(Thread.currentThread().getName() + "mission is over");
    }
}
         