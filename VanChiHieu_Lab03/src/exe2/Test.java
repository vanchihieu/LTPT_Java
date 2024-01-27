package exe2;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Test {
	public static void main(String[] args) {
		int[] array = { 9, 3, 7, 5, 1, 6, 8, 2, 4 };
		System.out.println("Mảng gốc: " + Arrays.toString(array));

		ForkJoinPool pool = ForkJoinPool.commonPool();
		MergeSortParallel mergeSort = new MergeSortParallel(array, 0, array.length - 1);
		pool.invoke(mergeSort);

		System.out.println("Mảng đã sắp xếp: " + Arrays.toString(array));
	}
}
