package parallel;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

public class SumArrayApp {

	public static void main(String[] args) {
		
		int n = 10_000_000;
		
		long []arr = LongStream.range(0l, n).toArray();
		
		SumArrayTask task = new SumArrayTask(arr, 0, n);
		
		ForkJoinPool pool = ForkJoinPool.commonPool();
		
		pool.invoke(task);
		
		pool.shutdown();
		
		System.out.println("Result: " + task.getResult().get());
	}
	
	private static class SumArrayTask extends RecursiveAction{
		private static final int THRESHOLD = 10_000;
		private long[] arr;
		private int lo;
		private int hi;
		
		private AtomicLong result = new AtomicLong(0l);
		
		public SumArrayTask(long[] arr, int lo, int hi) {
			this.arr = arr;
			this.lo = lo;
			this.hi = hi;
		}
		
		public AtomicLong getResult() {
			return result;
		}

		@Override
		protected void compute() {
			if(hi - lo < THRESHOLD) {
				long temp = Arrays.stream(arr, lo, hi).sum();
				result.addAndGet(temp);
			}else {
				int mid = (lo + hi) / 2;
				SumArrayTask left = new SumArrayTask(arr, lo, mid);
				SumArrayTask right = new SumArrayTask(arr, mid, hi);
				
				left.fork();
				right.fork();
				
				left.join();
				right.join();
				
				result.set(left.getResult().addAndGet(right.getResult().get()));
			}
		}
	}
}
