package thucHanhTuan01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance = 0;
    private Lock lock = new ReentrantLock();

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println("Deposited $" + amount + ". So Du: $" + balance);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrew $" + amount + ". So Du: $" + balance);
            } else {
                System.out.println("So du khong du.");
            }
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        return balance;
    }
}