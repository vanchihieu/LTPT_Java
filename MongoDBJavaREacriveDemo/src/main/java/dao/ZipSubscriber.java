package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class ZipSubscriber<T> implements Subscriber<T>{
	private List<T> received;
	private Subscription s;
	private CountDownLatch latch;
	
	public ZipSubscriber() {
		received = new ArrayList<>();
		latch = new CountDownLatch(1);
	}
	

	@Override
	public void onSubscribe(Subscription subscription) {
		this.s = subscription;
		this.s.request(1);
	}

	@Override
	public void onNext(T item) {
		received.add(item);
		this.s.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		throwable.printStackTrace();//???
		onComplete();
	}

	@Override
	public void onComplete() {
		latch.countDown();
		System.out.println("Completed!");
	}
	
	public List<T> getReceived() {
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		try {	
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return received;
	}
	
	public T getOneReceived() {
		
		return getReceived().size() > 0 ? received.get(0) : null;
	}
	
}