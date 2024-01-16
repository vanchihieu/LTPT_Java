package session01.mthread.ex02.bai04;

public class MyQueue {
	int n ;
	synchronized int get() {
		System.out.println("Got: "+ n);
		return n;
	}
	
	synchronized void put(int n) {
		this.n = n;
		System.out.println("Put: "+ n);
	}
}
