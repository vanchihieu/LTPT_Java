package chapter;

public class NumPrinter implements Runnable {
	private int n;

	public NumPrinter(int n) {
		super();
		this.n = n;
	}

	@Override
	public void run() {

		CharPrinter task = new CharPrinter('C', 10);

		Thread thread = new Thread(task, "Thread C");
		thread.start();
		for (int i = 0; i < n; i++) {
			System.out.printf("Thread %s print: %s \n", Thread.currentThread(), i);
			if (i == 5) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

}
