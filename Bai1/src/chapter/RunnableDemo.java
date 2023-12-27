package chapter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class RunnableDemo {
	
	public static void main(String[] args) {
		
		Runnable task = new TaskRunnable();
		Thread thread = new Thread(task);
		thread.start();
	}
}

class TaskRunnable implements Runnable {

	@Override
	public void run() {
		Random random = new Random();
		int total = 0;
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter("data/result.txt"), true);
			for (int i = 0; i < 10; i++) {
				int x = random.nextInt(1000);
				out.write(x + "; ");
				total += x;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Total: "+ total);
			out.close();
		}

	}

}