package session01.mthread.ex01.bai02;

public class TaskRun {
	public static void main(String[] args) {
		Runnable r1 = new AnotherTask("Collect Task", 15);
		Runnable r2 = new AnotherTask("Process Task", 19);
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		
//		AnotherTask r1 = new AnotherTask("Collect Task", 15);
//		AnotherTask r2 = new AnotherTask("Process Task", 19);
		
//		r1.start();
//		r2.start();
	}
}
