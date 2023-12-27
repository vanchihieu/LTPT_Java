package chapter;

public class ThreadDemo2 extends Thread{
    public static void main(String[] args) {
        ThreadDemo2 threadDemo2 = new ThreadDemo2();
        threadDemo2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }
}
