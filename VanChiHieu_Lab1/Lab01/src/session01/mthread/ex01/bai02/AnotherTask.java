package session01.mthread.ex01.bai02;

import java.util.Iterator;

public class AnotherTask extends Thread{
	private String taskName;
	private int counter;
	public AnotherTask(String taskName, int counter) {
		this.taskName = taskName;
		this.counter = counter;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < counter; i++) {
			System.out.println(taskName + "#"+i);
		}
	}
}
