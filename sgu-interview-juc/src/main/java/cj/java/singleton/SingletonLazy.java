package cj.java.singleton;

public class SingletonLazy {
    private static SingletonLazy singletonLazy;
    private SingletonLazy(){

    }

    public static SingletonLazy getSingletonLazy(){
        synchronized (SingletonLazy.class){
            if (singletonLazy==null){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                singletonLazy = new SingletonLazy();

            }
        }
        return singletonLazy;
    }


}
