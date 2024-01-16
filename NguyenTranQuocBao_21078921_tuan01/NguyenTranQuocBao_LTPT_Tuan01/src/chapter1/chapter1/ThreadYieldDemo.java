package chapter1;

public class ThreadYieldDemo {
    public static void main(String[] args) {
    	Runnable r = () -> {
            int counter = 0;
            while (counter < 2) {
                System.out.println(Thread.currentThread().getName());
                counter++;
                Thread.yield();
            }
        };
        new Thread(r).start();
        new Thread(r).start();
    }
}