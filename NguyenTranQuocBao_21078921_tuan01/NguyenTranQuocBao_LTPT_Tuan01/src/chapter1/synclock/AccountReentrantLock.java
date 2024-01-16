package chapter1.synclock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountReentrantLock {
    private String accountNo;
    private String name;
    private double balance;
    
    private static Lock lock = new ReentrantLock(); // Khoa cong bang
    
    public AccountReentrantLock(String accountNo, String name) {
        this.accountNo = accountNo;
        this.name = name;
        this.balance = 100.0;
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
            }
        } finally {
          lock.unlock();  
        }
    }
    
    public double withdraw(double amount) {
        try {
            lock.lock();
            if (amount > balance) {
                return 0;
            }
            
            balance -= amount;
        } finally {
          lock.unlock();  
        }
        
        return amount;
    }
}
