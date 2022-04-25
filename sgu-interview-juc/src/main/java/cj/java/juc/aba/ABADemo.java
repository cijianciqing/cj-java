package cj.java.juc.aba;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题的解决          AtomicStampedReference
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        System.out.println("-----------------ABA问题的产生--------------------");
        new Thread("t1") {
            @Override
            public void run() {
                atomicReference.compareAndSet(100, 101);
                atomicReference.compareAndSet(101, 100);
            }
        }.start();

        new Thread("t2") {
            @Override
            public void run() {
                try {
                    //线程t2休眠1秒钟,确保t1完成一次ABA操作
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicReference.compareAndSet(100, 2020) + "\t" + atomicReference.get());
            }
        }.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

