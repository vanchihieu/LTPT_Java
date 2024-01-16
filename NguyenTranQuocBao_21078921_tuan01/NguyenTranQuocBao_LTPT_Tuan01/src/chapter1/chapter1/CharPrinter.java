package chapter1;

public class CharPrinter implements Runnable {

	private char ch;
	private int num;
	
	
	public CharPrinter(char ch, int num) {
		this.ch = ch;
		this.num = num;
	}

	@Override
	public void run() {
		
		for (int i = 0; i < num; i++) {
			System.out.print(ch + " ");
		}
		
		System.out.println("\n");
		System.out.printf("\n%s print %s\n", Thread.currentThread(), ch);
	}
}
