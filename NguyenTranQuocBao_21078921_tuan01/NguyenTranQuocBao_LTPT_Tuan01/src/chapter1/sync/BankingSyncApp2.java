package chapter1.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BankingSyncApp2 {
    private static AccountNormal account = new AccountNormal("0001", "Account1");
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        double money = 0.0;
        
        Callable<Double> withdraw = () -> { // block statement
            double x = 0.0;
            synchronized(account) {
                x = account.withdraw(1.0);
            }
            return x;
        };
        
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<Double>> fs = new ArrayList<Future<Double>>();
        
        for (int i=0; i < 100; i++) {
            fs.add(service.submit(withdraw));
        }
        
        service.shutdown();
        while(!service.isTerminated()){}
        
        for(Future<Double> f:fs) {
            money +=f.get();
        }
        
        System.out.println("Money: " + money);
        System.out.println("Balance: " + account.getBalance());
       
    }
}

