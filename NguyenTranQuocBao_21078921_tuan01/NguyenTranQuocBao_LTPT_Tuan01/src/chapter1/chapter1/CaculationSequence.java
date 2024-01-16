package chapter1;

public class CaculationSequence {

	public static void main(String[] args) {
		// MAX-INT: 2147483647
		long n = 3000000000l;
		long total = 0l;
		for(long i=0l; i< n; i++) {
			total += i;
		}

		System.out.print("Total: " + total);
	}

}
