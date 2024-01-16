package chapter1;

import java.util.concurrent.Callable;

public class CaculationNumRangeCallable implements Callable<Long>{
	private long noFrom;
	private long noTo;
	
	public CaculationNumRangeCallable(long noFrom, long noTo) {
		this.noFrom = noFrom;
		this.noTo = noTo;
	}

	@Override
	public Long call() throws Exception {
		long total = 0l;
		
		for (long i = noFrom; i < noTo; i++) {
			total += i;
		}
		
		return total;
	}
	
}
