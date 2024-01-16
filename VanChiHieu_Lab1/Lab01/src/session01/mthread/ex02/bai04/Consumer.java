package session01.mthread.ex02.bai04;

public class Consumer implements Runnable{
	MyQueue q;
	
	public Consumer(MyQueue q) {
		this.q = q;
		new Thread(this, "Consumer").start();
	}

	@Override
	public void run() {
		while(true) {
			q.get();
		}
	}

}
