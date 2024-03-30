package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class ReactiveDemo {
	public static void main(String[] args) {
		SubmissionPublisher<String> publisher = 
				new SubmissionPublisher<String>();
		
		
		StringSubscriber subscriber = new StringSubscriber();
		
		publisher.subscribe(subscriber);
		
		List<String> names = List.of("Lan","Nga", "Binh");
		
		names.forEach(x -> {
			publisher.submit(x);
		});
		
		publisher.submit("Bich");
		
		List<String> rs = subscriber.getReceived();
		System.out.println(rs);
		
		publisher.close();
	}
}

class StringSubscriber implements Subscriber<String>{
	private List<String> received;
	private Subscription s;
	
	public StringSubscriber() {
		received = new ArrayList<String>();
	}
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.s = subscription;
		this.s.request(1);
	}

	@Override
	public void onNext(String item) {
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
	
	public List<String> getReceived() {
		return received;
	}
}
