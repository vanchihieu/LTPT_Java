package chapter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Callable<Integer> task = new Task();
		FutureTask<Integer> call = new FutureTask<Integer>(task);
		
		new Thread(call).start();
		
		int total = call.get();
		System.out.println("Total: "+ total);
	}
}

class Task implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		Random random = new Random();
		int total = 0;
		PrintWriter out = null;
		out = new PrintWriter(new FileWriter("data/result.txt"), true);
		for (int i = 0; i < 10; i++) {
			int x = random.nextInt(1000);
			out.write(x + "; ");
			total += x;
		}
		out.close();
		return total;
	}
}