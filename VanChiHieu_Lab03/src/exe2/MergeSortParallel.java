package exe2;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MergeSortParallel extends RecursiveAction {
	private final int[] array;
	private final int start;
	private final int end;

	public MergeSortParallel(int[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if (start < end) {
            int mid = (start + end) / 2;

            // Chia mảng thành hai nửa
            MergeSortParallel leftTask = new MergeSortParallel(array, start, mid);
            MergeSortParallel rightTask = new MergeSortParallel(array, mid + 1, end);

            // Gọi các subtask song song
            invokeAll(leftTask, rightTask);

            // Gộp các nửa đã sắp xếp
            merge(array, start, mid, end);
        }
	}
	
	private void merge(int[] array, int start, int mid, int end) {
        int[] left = Arrays.copyOfRange(array, start, mid + 1);
        int[] right = Arrays.copyOfRange(array, mid + 1, end + 1);

        int i = 0, j = 0, k = start;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
}
