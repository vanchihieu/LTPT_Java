package exe1;

import java.util.concurrent.RecursiveTask;

public class SumRecursiveTask extends RecursiveTask<Integer> {
	private static final int THRESHOLD = 1000; // Ngưỡng để chuyển sang tính toán tuần tự
	private int[] array;
	private int start;
	private int end;

	public SumRecursiveTask(int[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		if (end - start <= THRESHOLD) {
			// Trường hợp cơ bản: tính tổng tuần tự
			int sum = 0;
			for (int i = start; i < end; i++) {
				sum += array[i];
			}
			return sum;
		} else {
			// Trường hợp đệ quy: chia công việc thành các subtask
			int middle = (start + end) / 2;
			SumRecursiveTask leftTask = new SumRecursiveTask(array, start, middle);
			SumRecursiveTask rightTask = new SumRecursiveTask(array, middle, end);

			leftTask.fork(); // Chia nhánh subtask bên trái
			int rightSum = rightTask.compute(); // Tính toán subtask bên phải
			int leftSum = leftTask.join(); // Chờ subtask bên trái hoàn thành và lấy kết quả

			return leftSum + rightSum;
		}
	}
}
