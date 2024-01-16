package chapter1;

public class NumTaskRunnable implements Runnable{
	private int n;

	public NumTaskRunnable(int n) {
		this.n = n;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < n; i++) {
			System.out.print(i + " ");
//			Thread.yield();

		}
	}
}
