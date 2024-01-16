package session01.mthread.ex02.bai01;

public class TestJoinThread {
	public static void main(String[] args) {
		new Thread(new YourTask()).start();
	}
}
