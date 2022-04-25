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

        System.out.println("-----------------ABA问题的解决--------------------");

        new Thread("t3") {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                System.out.println(getName() + "\t第一次版本号：" + stamp);
                try {
                    //t3线程休眠1秒中,确保t4也拿到初始的版本号
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean result3_2 = atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(getName() + "\t第二次版本号：" + atomicStampedReference.getStamp()+" result: "+result3_2);
                boolean result3_3 = atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(getName() + "\t第三次版本号：" + atomicStampedReference.getStamp()+" result: "+result3_3);
            }
        }.start();

        new Thread("t4") {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                System.out.println(getName() + "\t第一次版本号：" + stamp);
                try {
                    //t4线程休眠3秒中,确保t3完成一次ABA操作
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean result = atomicStampedReference.compareAndSet(100, 2020, stamp, stamp + 1);
                System.out.println(getName() + "\t是否修改成功," + result + "\t当前最新实际版本号：" + atomicStampedReference.getStamp());
                System.out.println(getName() + "\t当前实际最新值：" + atomicStampedReference.getReference());
            }
        }.start();
    }
}

