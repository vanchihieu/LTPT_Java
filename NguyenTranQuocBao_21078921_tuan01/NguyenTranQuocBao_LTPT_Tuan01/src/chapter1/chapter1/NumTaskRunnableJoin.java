package chapter1;

public class NumTaskRunnableJoin extends Thread{
	private int n;

	public NumTaskRunnableJoin(int n) {
		this.n = n;
	}
	
	@Override
	public void run() {
		Thread t = new Thread(new CharPrinter('C', 10));
		t.start();
		for (int i = 0; i < n; i++) {
			System.out.print(i + " ");
			if (i==10) {
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}
