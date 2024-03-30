package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class BikeSubscriber<T> implements Subscriber<T> {
	private List<T> results;
	private Subscription sub;
	private CountDownLatch latch;
	
	public BikeSubscriber() {
		results = new ArrayList<>();
		latch = new CountDownLatch(1);
	}
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.sub = subscription;
		sub.request(1);
	}

	@Override
	public void onNext(T item) {
		results.add(item);
		this.sub.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() {
		latch.countDown();
	}
	
//	Get single result
	public T getSingleResult() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return results.size() > 0 ? results.get(0) : null;
	}
//	Get list result
	public List<T> getResults(){
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return results;
	}

}
