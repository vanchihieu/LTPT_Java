package chapter;

public class JoinDemo2 {
	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		
		Thread thread1 = new Thread(new CharPrinter('A', 20), "One");
		Thread thread2 = new Thread(new CharPrinter('B', 20), "Two");
		Thread thread3 = new Thread(new NumPrinter(10));
		
		thread3.setPriority(7);
		thread1.setPriority(1);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
//		thread1.join();
		
		while(thread1.isAlive() || thread2.isAlive() || thread3.isAlive()) {}
		
		long endTime = System.nanoTime();
		System.out.println("Total time: "+ (endTime - startTime));

	}
	

	
}
