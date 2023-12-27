package chapter;

public class CharPrinter implements Runnable{
    private char ch;
    private int n;

    public CharPrinter(char ch, int n) {
        this.ch = ch;
        this.n = n;
    }


    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.printf("Thread %s print %s\n", Thread.currentThread(), ch);
        }
            System.out.println();
    }
}
