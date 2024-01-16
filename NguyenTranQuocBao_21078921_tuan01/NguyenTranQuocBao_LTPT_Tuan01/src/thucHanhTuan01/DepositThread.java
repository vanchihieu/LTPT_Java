package thucHanhTuan01;

public class DepositThread extends Thread {
	private BankAccount account;

	public DepositThread(BankAccount account) {
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
            account.deposit(100);
        }
    }
}
