package demo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {
	public static void main(String[] args) {
		
		
		Map<String, List<String>> dic = new TreeMap<String, List<String>>();
		dic.put("Nga", List.of("034535434", "234234234"));
		dic.put("An", List.of("034535434", "234234234"));
		dic.put("Binh", List.of("034535434", "234234234"));
		
		List<String> names = dic
		.entrySet()
		.stream()
		.map(x -> (x.getKey()))
		.collect(Collectors.toList());
		
//		System.out.println(names);
		
		List<String> list = List.of(
					"Lan", "Nga", "Binh", "Nam", "Nam"
				);
		
		Stream<String> temp = list.stream().filter(x -> (x.charAt(0)=='N'));
		
		 Set<String> rs = list
		.stream()
		.filter(x -> (x.charAt(0)=='N'))
		.collect(Collectors.toSet());
		
		System.out.println(rs);
//		
//		IntStream
//		.range(1, 100)
//		.map(x -> (x*2))
//		.forEach(System.out::println);
	}
}
