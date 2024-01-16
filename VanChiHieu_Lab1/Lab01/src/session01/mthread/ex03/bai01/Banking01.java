package session01.mthread.ex03.bai01;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Banking01 {
	private static Account acc = new Account("Hieu", "318");

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Double> withDrawTask = () -> {
			return acc.withdraw(100);
		};

		Runnable depositTask = () -> {
//			for (int i = 0; i < 10; i++) {
//				acc.deposit(100);
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}

			acc.deposit(1000);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		};

		ExecutorService service = Executors.newCachedThreadPool();

		service.submit(depositTask);

		ArrayList<Future<Double>> listWithDraw = new ArrayList<Future<Double>>();

		for (int i = 0; i < 2; i++) {
			listWithDraw.add(service.submit(withDrawTask));
		}

		double total = 0;
		for (Future<Double> future : listWithDraw) {
			total += future.get();
		}

		service.shutdown();

		while (!service.isTerminated()) {
		}

		System.out.println("With draw amount: " + total);
		System.out.println("Balance: " + acc.getBalance());
	}

}
