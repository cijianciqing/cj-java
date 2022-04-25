package cj.java.thread;

public class CJThreadDemo {
    public static void main(String[] args) {

        Thread thread = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" ----> start !!");
        },"t1");

        thread.start();
    }
}
