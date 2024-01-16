package session01.mthread.ex02.bai05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Producer_Consumer_Demo {
	public static void main(String[] args) {
		System.out.println("Press Control-C to stop.");
		ExecutorService service = Executors.newFixedThreadPool(2);

		MyQueue q = new MyQueue();

		service.execute(new Producer(q));
		service.execute(new Consumer(q));
	}
}
