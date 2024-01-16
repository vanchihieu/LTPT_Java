package chapter1;

public class ThreadJoinDemo {

	public static void main(String[] args) {
		CharPrinter task1 = new CharPrinter('A', 10);
		CharPrinter task2 = new CharPrinter('B', 10);
		NumTaskRunnableJoin task3 = new NumTaskRunnableJoin(20);
		
		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(task2);
		Thread thread3 = new Thread(task3);
		
		
		thread1.start();
		thread2.start();
		thread3.start();

	}

}
