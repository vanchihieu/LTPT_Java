package parallel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class CounterDemo {
	
	private static Counter counter = new Counter();
	
	public static void main(String[] args) {
		Runnable task = () -> {
			counter.increment();
		};
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 1000; i++) {
			executor.execute(task);
		}
		
		executor.shutdown();
		
		while (!executor.isTerminated()) {

		}
		
		System.out.println(counter.getCount());
	}
	
	private static class Counter{
		private AtomicLong count = new AtomicLong(0l);

		public void increment() {
			count.incrementAndGet();
		}

		public long getCount() {
			return count.get();
		}
	}
}
