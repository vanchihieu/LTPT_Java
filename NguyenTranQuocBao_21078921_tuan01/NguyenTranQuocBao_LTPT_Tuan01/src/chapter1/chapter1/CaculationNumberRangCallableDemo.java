package chapter1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CaculationNumberRangCallableDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Callable<Long> task1 = new CaculationNumRangeCallable(0, 10000000000l);
		Callable<Long> task2 = new CaculationNumRangeCallable(10000000000l, 20000000000l);
		Callable<Long> task3 = new CaculationNumRangeCallable(20000000000l, 30000000000l);
		
		FutureTask<Long> f1 = new FutureTask<>(task1);
		FutureTask<Long> f2 = new FutureTask<>(task2);
		FutureTask<Long> f3 = new FutureTask<>(task3);
		
		Thread thread1 = new Thread(f1);
		Thread thread2 = new Thread(f2);
		Thread thread3 = new Thread(f3);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		long total = f1.get() + f2.get() + f3.get();
		
		System.out.println("Total: " + total);
	}

} 
