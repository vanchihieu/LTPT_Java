package parallel;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class SumArrayApp2 {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		int n = 10_000_000;
		
		long []arr = LongStream.range(0l, n).toArray();
		
		SumArrayTask task = new SumArrayTask(arr, 0, n);
		
		ForkJoinPool pool = ForkJoinPool.commonPool();
		
		pool.invoke(task);
		
		pool.shutdown();
		
		System.out.println("Result: " + task.get());
	}
	
	private static class SumArrayTask extends RecursiveTask<Long>{
		
		private static final int THRESHOLD = 10_000;
		private long[] arr;
		private int lo;
		private int hi;
		
		public SumArrayTask(long[] arr, int lo, int hi) {
			super();
			this.arr = arr;
			this.lo = lo;
			this.hi = hi;
		}
		
		public long[] getArr() {
			return arr;
		}

		@Override
		protected Long compute() {
			if(hi - lo < THRESHOLD)
				return Arrays.stream(arr, lo, hi).sum();
			
			int mid = (lo + hi) / 2;
			SumArrayTask left = new SumArrayTask(arr, lo, mid);
//			printArr(arr, lo, mid);
			
			SumArrayTask right = new SumArrayTask(arr, mid, hi);
//			printArr(arr, mid, hi);
			
			left.fork();
			right.fork();
			
			return left.join() + right.join();
		}

		private void printArr(int[] arr, int lo, int hi) {
			Arrays.stream(arr, lo, hi).forEach(x -> System.out.print(x + "\t"));
			System.out.println();
		}
		
		

	}

}
