package chapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class SumRangeDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		long startTime = System.nanoTime();
		
		long n = 2001;
		
		long a = 0l;
		long b = 1001;
		long s = 1001;
		
		List<Callable<Long>> tasks = new ArrayList<>();
		
		ExecutorService service = Executors.newFixedThreadPool(9);
		
		while(b <= n) {
			tasks.add(new SumRangeTask(a, b));
			a += s;
			b += s;
		}
		
		List<Future<Long>> fs = service.invokeAll(tasks);
		
		service.shutdown();
		
		while(!service.isTerminated()) {
			
		}
		
		long total = 0l;
		
		
		Callable<Long> task1 = new SumRangeTask(0, 1001);
		Callable<Long> task2 = new SumRangeTask(1001, 2001);

		FutureTask<Long> f1, f2;
		Thread thread1 = new Thread(f1 = new FutureTask<>(task1));
		Thread thread2 = new Thread(f2 = new FutureTask<>(task2));

		thread1.start();
		thread2.start();

		System.out.println("Total: " + (f1.get() + f2.get()));

		long endTime = System.nanoTime();
		System.out.println("Total time: "+ (endTime - startTime));
	}
}
