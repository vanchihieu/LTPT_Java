package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class RestaurantSubcriber<T> implements Subscriber<T> {
	private List<T> results;
	private Subscription sub;
	private CountDownLatch latch;
	
	public RestaurantSubcriber() {
		results = new ArrayList<>();
		latch = new CountDownLatch(1);
	}
	
	@Override
	public void onSubscribe(Subscription s) {
		this.sub = s;
		sub.request(1);
	}

	@Override
	public void onNext(T t) {
		results.add(t);
		this.sub.request(1);
	}

	@Override
	public void onError(Throwable t) {
		t.printStackTrace();
	}

	@Override
	public void onComplete() {
		latch.countDown();
	}
	
	public T getSingleResult() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return results.size() > 0 ? results.get(0) : null;
	}
	
	public List<T> getResults() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return results;
	}
}
