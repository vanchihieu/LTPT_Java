package thucHanhTuan01;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Thread[] depositThreads = new Thread[10];
        Thread[] withdrawThreads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            depositThreads[i] = new DepositThread(account);
            withdrawThreads[i] = new WithdrawThread(account);
        }

        for (Thread thread : depositThreads) {
            thread.start();
        }

        for (Thread thread : withdrawThreads) {
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : depositThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread thread : withdrawThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("So Du Cuoi: $" + account.getBalance());
    }
}