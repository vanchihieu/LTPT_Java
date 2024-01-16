package session01.mthread.ex02.bai04;

public class Producer implements Runnable{
	MyQueue q;
	
	public Producer(MyQueue q) {
		this.q = q;
		new Thread(this, "Producer").start();
	}

	@Override
	public void run() {
		int i = 0;
		while(true) {
			q.put(i++);
		}
	}

}
