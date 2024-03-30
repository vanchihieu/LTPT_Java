package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class IntStreamDemo {
	public static void main(String[] args) {
		SubmissionPublisher<Integer> publisher = 
				new SubmissionPublisher<Integer>();
		
		List<Integer> list = List.of(32,42,454,5);
		
		IntSubscriber subscriber = new IntSubscriber();
		
		publisher.subscribe(subscriber);
		
		list.forEach(publisher::submit);
		
		subscriber.getReceived().forEach(System.out::println);
		
		publisher.close();
	}
}

class IntSubscriber implements Subscriber<Integer>{
	
	private List<Integer> received;
	private Subscription s;

	public IntSubscriber() {
		received = new ArrayList<Integer>();
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.s = subscription;
		this.s.request(1);
	}

	@Override
	public void onNext(Integer item) {
		received.add(item);
		this.s.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() {
		System.out.println("Completed!");
	}
	
	public List<Integer> getReceived() {
		return received;
	}
}
