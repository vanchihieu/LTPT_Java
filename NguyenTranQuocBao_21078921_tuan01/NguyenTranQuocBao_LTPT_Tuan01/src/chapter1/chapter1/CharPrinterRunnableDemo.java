package chapter1;

public class CharPrinterRunnableDemo {

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		
		Runnable task1 = new CharPrinter('A', 20);
		Runnable task2 = new CharPrinter('B', 10);
		
		Thread thread1 = new Thread(task1);
		thread1.setName("Thread One");
		
		Thread thread2 = new Thread(task2);
		thread2.setName("Thread Two");
		
		// Set độ ưu tiên 
		// thread1.setPriority(10);
		
		thread1.start();
		thread2.start();

		
		System.out.print(thread1.isAlive());
		System.out.print(thread2.isAlive());
		
		while(thread1.isAlive() && thread2.isAlive()) {
			
		}
		
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		
		
		System.out.print("\nTotal time: " + totalTime + "\n");
	}
}
