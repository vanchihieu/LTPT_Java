package chapter;

import java.util.concurrent.Callable;

public class SumRangeTask implements Callable<Long>{
	private long a;
	private long b;
	
	
	public SumRangeTask(long a, long b) {
		super();
		this.a = a;
		this.b = b;
	}


	@Override
	public Long call() throws Exception {
		long total = 0l;
		
		for (long i = a; i < b; i++) {
			System.out.printf("%s cal %s\n", Thread.currentThread(), i);
			total += i;
		}
		return total;
	}

}
