package exe1;

import java.util.concurrent.ForkJoinPool;

public class Test {
	public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Sử dụng RecursiveTask
        SumRecursiveTask task = new SumRecursiveTask(array, 0, array.length);
        ForkJoinPool pool = new ForkJoinPool();
        int result = pool.invoke(task);
        System.out.println("Tổng (RecursiveTask): " + result);

        // Sử dụng RecursiveAction
        SumRecursiveAction action = new SumRecursiveAction(array, 0, array.length);
        pool.invoke(action);
        result = action.getResult();
        System.out.println("Tổng (RecursiveAction): " + result);
    }
}
