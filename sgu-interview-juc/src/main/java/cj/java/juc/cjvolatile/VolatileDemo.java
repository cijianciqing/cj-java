package cj.java.juc.cjvolatile;

import java.util.concurrent.atomic.AtomicInteger;


class MyData {
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    /*
    * 用于验证 jvm的原子性操作
    * 方法添加synchronized关键字后，得到想要的结果-->20000
    * */
    public   void addPlusPlus() {
        this.number++;
    }


}

/**
 * 验证volatile的可见性a
 * 1.当number未被volatile修饰时，new Thread将number值改为60，但main线程并不知道，会一直在循环中出不来
 * 2.当number使用volatile修饰，new Thread改变number值后，会通知main线程主内存的值已被修改，结束任务。体现了可见性
 */
public class VolatileDemo {
    public static void main(String[] args) {
//        seeByVolatile();
        atomic();
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


    //验证原子性
    public static void atomic() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 1000; j++) {
                        myData.addPlusPlus();
                    }
                }
            }).start();
        }

        //等待上面20个线程全部计算结束
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        //如果是原子性，则最终结果应为20000
        System.out.println(Thread.currentThread().getName() + "int finally number is " + myData.number);
    }
}
         