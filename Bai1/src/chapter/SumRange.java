package chapter;

public class SumRange {
	public static void main(String[] args) {
		long startTime = System.nanoTime();

		long total = 0l;
		for (long i = 0l; i < 2001; i++) {
			total += i;
		}
		
		System.out.println("Total: " + total);

		long endTime = System.nanoTime();
		System.out.println("Total time: "+ (endTime - startTime));
	}
}
