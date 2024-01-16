package chapter1.synlocksignal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	private String accountNo;
    private String name;
    private double balance;
    
    private static Lock lock = new ReentrantLock(); // Khoa cong bang
    private static Condition condition =  lock.newCondition();
    
    public Account(String accountNo, String name) {
        this.accountNo = accountNo;
        this.name = name;
        this.balance = 0;
    }
    
    public String getAccountNo() {
        return accountNo;
    }
    
    public String getName() {
        return name;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        try {
            lock.lock();
            if(amount > 0) {
                balance += amount;
                System.out.printf("%s deposit %f, balance %f\n", Thread.currentThread().getName(), amount, balance);
                condition.signalAll();
            }
        } finally {
          lock.unlock();  
        }
    }
    
    public double withdraw(double amount) throws InterruptedException {
        try {
            lock.lock();
            while (amount > balance) {
                System.out.println("Not enought money. Waiting ...");
                condition.await(1000, TimeUnit.MILLISECONDS);
                
            }
            
            balance -= amount;
            System.out.printf("%s withdraw %f, balance %f\n", Thread.currentThread().getName(), amount, balance);
            
        } finally {
          lock.unlock();  
        }
        
        return amount;
    }
}
