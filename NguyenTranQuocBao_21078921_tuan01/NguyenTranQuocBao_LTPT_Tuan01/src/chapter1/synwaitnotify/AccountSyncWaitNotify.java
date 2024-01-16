package chapter1.synwaitnotify;

public class AccountSyncWaitNotify {
    private String accountNo;
    private String name;
    private double balance;
    
    public AccountSyncWaitNotify(String accountNo, String name) {
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
    
    public synchronized void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            notifyAll();
            System.out.printf("%s deposit %f, balance %f\n", Thread.currentThread().getName(), amount, balance);
        }
    }
    
    public synchronized double withdraw(double amount) {
        while (amount > balance) {
            System.out.printf("%s Not enough money. Waiting ...\n", Thread.currentThread().getName());
            try {
				wait(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
        }
        
        balance -= amount;
        System.out.printf("%s withdraw %f, balance %f\n", Thread.currentThread().getName(), amount, balance);
        return amount;
    }
}
