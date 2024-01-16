package session01.mthread.ex03.bai01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Banking {
	private static Account account = new Account("1001", "Hieu"); // balance = 0

	public static void main(String[] args) {
		Runnable task = () -> {
			synchronized (account) {
				account.deposit(1);
			}
		};

		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			service.submit(task);
		}

		service.shutdown();

		while (!service.isTerminated()) {
		}

		System.out.println("Balance: " + account.getBalance());
	}
}
