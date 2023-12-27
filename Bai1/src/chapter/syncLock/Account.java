package chapter.syncLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	private static Lock lock = new ReentrantLock();

	private String id;
	private String name;
	private double balance;

	public Account(String id, String name) {
		this.id = id;
		this.name = name;
		this.balance = 0.0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

//	public synchronized void deposit(double amount) {
	public void deposit(double amount) {
		lock.lock();
		try {
			if (amount > 0)
				balance += amount;
		} finally {

			lock.unlock();
		}
	}

	public double withdraw(double amount) {
		if (amount > balance) {
			return 0;
		}

		balance -= amount;

		return amount;
	}
}
