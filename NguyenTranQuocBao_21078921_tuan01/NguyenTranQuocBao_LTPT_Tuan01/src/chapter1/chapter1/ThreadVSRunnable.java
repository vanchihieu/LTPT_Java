package chapter1;

class ImplementRunnable implements Runnable {
	private int counter = 0;
	@Override
	public void run() {
		counter++;
		System.out.println("ImplementRunnable - Thread name: " + Thread.currentThread().getName() + " - counter: " + counter);	
	}
}

class ExtendThread extends Thread {
	private int counter = 0;
	@Override
	public void run() {
		counter++;
		System.out.println("ExtendThread - Thread name" + Thread.currentThread().getName() + " - counter: " + counter);
	}
}

public class ThreadVSRunnable {

	public static void main(String[] args) throws InterruptedException {
		
		ImplementRunnable r = new ImplementRunnable();
		
		Thread t1 = new Thread(r);
		t1.start();
		Thread.sleep(1000);
		
		Thread t2 = new Thread(r);
		t2.start();
		Thread.sleep(1000);
		
		Thread t3 = new Thread(r);
		t3.start();
		Thread.sleep(1000);

		ExtendThread et1 = new ExtendThread();
		et1.start();
		Thread.sleep(1000);
		
		ExtendThread et2 = new ExtendThread();
		et2.start();
		Thread.sleep(1000);
		
		ExtendThread et3 = new ExtendThread();
		et3.start();
		Thread.sleep(1000);
	}

}
