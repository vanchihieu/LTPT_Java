package exe1;

import java.util.concurrent.RecursiveAction;

public class SumRecursiveAction extends RecursiveAction{
	  private static final int THRESHOLD = 1000; // Ngưỡng để chuyển sang tính toán tuần tự
	    private int[] array;
	    private int start;
	    private int end;
	    private int result;

	    public SumRecursiveAction(int[] array, int start, int end) {
	        this.array = array;
	        this.start = start;
	        this.end = end;
	    }
	    
	@Override
	protected void compute() {
		if (end - start <= THRESHOLD) {
            // Trường hợp cơ bản: tính tổng tuần tự
            for (int i = start; i < end; i++) {
                result += array[i];
            }
        } else {
            // Trường hợp đệ quy: chia công việc thành các subtask
            int middle = (start + end) / 2;
            SumRecursiveAction leftTask = new SumRecursiveAction(array, start, middle);
            SumRecursiveAction rightTask = new SumRecursiveAction(array, middle, end);

            leftTask.fork(); // Chia nhánh subtask bên trái
            rightTask.compute(); // Tính toán subtask bên phải
            leftTask.join(); // Chờ subtask bên trái hoàn thành
        }		
	}

	public int getResult() {
        return result;
    }
}
