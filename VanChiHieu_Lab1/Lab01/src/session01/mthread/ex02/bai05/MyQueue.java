package session01.mthread.ex02.bai05;

public class MyQueue {
	int n ;
	boolean valueSet = false;

	synchronized int get() {
		if(!valueSet)
			try {
				Thread.sleep(300);
			} catch (Exception e) {
			}
		valueSet = false;
		notify();
		return n;
	}
	
	synchronized void put(int n) {
		if(valueSet)
			try {
				wait();
			} catch (InterruptedException e) {
			}
		this.n = n;
		valueSet = true;
		System.out.println("Put: "+n);
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
		notify();
	}
}
