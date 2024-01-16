package chapter1;

public class RunnableDemo {
	public static void main(String[] args) {
//		NumTaskRunnable num = new NumTaskRunnable(10);
//		Thread t = new Thread(num);
//		t.start();
		
		new Thread(new NumTaskRunnable(10)).start();
	}
}
