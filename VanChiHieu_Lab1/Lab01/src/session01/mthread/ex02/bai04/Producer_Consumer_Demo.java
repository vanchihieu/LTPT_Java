package session01.mthread.ex02.bai04;

public class Producer_Consumer_Demo {
	public static void main(String[] args) {
		MyQueue q = new MyQueue();
		new Producer(q);
		new Consumer(q);
	}
}
