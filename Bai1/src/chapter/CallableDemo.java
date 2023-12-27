package chapter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Long> task = new MyTask(0, 10);
		FutureTask<Long> fuTask = new FutureTask<>(task);

		Thread thread = new Thread(fuTask);
		thread.start();

		System.out.println(fuTask.get());

	}

}

class MyTask implements Callable<Long> {
	private int a;
	private int b;

	public MyTask(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public Long call() throws Exception {
		long total = 0;

		for (int i = a; i < b; i++) {
			total += i;
		}

		return total;
	}

}