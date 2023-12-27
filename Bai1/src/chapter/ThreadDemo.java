package chapter;

public class ThreadDemo {
    public static void main(String[] args) {
        Runnable task1 = new CharPrinter('A', 10);
        Runnable task2 = new CharPrinter('B', 10);
        Runnable task3 = new NumPrinter(50);
        
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.setName("Thread 1");
        thread2.setName("Thread 2");
        thread3.setName("Thread three");

//        thread3.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
