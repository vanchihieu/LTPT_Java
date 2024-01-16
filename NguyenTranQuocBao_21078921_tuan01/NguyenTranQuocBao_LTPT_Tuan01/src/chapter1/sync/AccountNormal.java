package chapter1.sync;

public class AccountNormal {
    private String accountNo;
    private String name;
    private double balance;
    
    public AccountNormal(String accountNo, String name) {
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
        if(amount > 0) {
            balance += amount;
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
