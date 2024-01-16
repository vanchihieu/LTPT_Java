package chapter1.sync;

public class AccountSync {
    private String accountNo;
    private String name;
    private double balance;
    
    public AccountSync(String accountNo, String name) {
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
        }
    }
    
    public synchronized double withdraw(double amount) {
        if (amount > balance) {
            return 0;
        }
        
        balance -= amount;
        
        return amount;
    }
}
