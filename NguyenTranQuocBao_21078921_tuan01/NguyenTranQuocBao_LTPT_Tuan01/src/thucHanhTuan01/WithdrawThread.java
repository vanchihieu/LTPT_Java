package thucHanhTuan01;

public class WithdrawThread extends Thread {
    private BankAccount account;

    public WithdrawThread(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.withdraw(100);
        }
    }
}