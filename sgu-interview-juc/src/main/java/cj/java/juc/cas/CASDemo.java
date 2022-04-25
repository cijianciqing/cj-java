package cj.java.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.CAS是什么？    -->compare And Swap
 * 比较并交换
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019));
        System.out.println(atomicInteger.compareAndSet(5, 2020));
    }
}
