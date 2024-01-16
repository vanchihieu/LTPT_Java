package chapter1.synlocksignal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BankingApp {
	  private static Account account = new Account("0001", "Test1");
	  public static void main(String[] args) throws InterruptedException, ExecutionException {
	        double money = 0.0;
	        // Tai khoan hien tai co 100$ -> muon rut 200$
	        Callable<Double> withdrawTask = () -> {
	            return account.withdraw(300);
	        };
	        
	        // Nap tien
	        
	        Runnable depositTask = () -> {
	            for (int i=0; i < 10; i++) {
	                
	                account.deposit(20);
	                try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        };
	        
	        ExecutorService service = Executors.newCachedThreadPool();
	        
	        Future<Double> fs = service.submit(withdrawTask);
	        service.submit(depositTask);
	        
	        service.shutdown();
	        while(!service.isTerminated()){}
	        
	        money = fs.get();
	        System.out.println("Money: " + money);
	        System.out.println("Balance: " + account.getBalance());
	    }
}
