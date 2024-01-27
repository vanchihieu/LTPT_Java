package parallel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class StreamDemo {
	public static void main(String[] args) {
		
		//sequential stream
		
		//parallel stream
		
		
		int n = 10_000_000;
		
		List<Long> list = LongStream  
				.range(0, n)
				.boxed()
				.collect(Collectors.toList());	
		
		long start = System.currentTimeMillis();
		
		Long total = list.parallelStream().reduce(0l, Long::sum);
		System.out.println("Total: " + total);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time taken: " + (end - start) + " ms");
	}
}

//primitive type
//byte short int long float double char boolean

//wrapper type
//Byte Short Integer Long Float Double Character Boolean

//autoboxing and unboxing
//Integer i = 10; //autoboxing
//
//int j = i; //unboxing
