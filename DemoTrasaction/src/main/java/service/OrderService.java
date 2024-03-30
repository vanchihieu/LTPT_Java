package service;

import org.bson.types.ObjectId;

import com.mongodb.reactivestreams.client.ClientSession;
import com.mongodb.reactivestreams.client.MongoClient;

import dao.BikeSubscriber;
import dao.OrderDao;
import dao.ProductDao;
import db.Connection;

public class OrderService {
	private OrderDao orderDao;
	private ProductDao productDao;
	
	public OrderService() {
		orderDao = new OrderDao();
		productDao = new ProductDao();
	}
	
	public String returnOrder(ClientSession clientSession,ObjectId orderId,long productId) {
		MongoClient client = Connection.getInstance().getClient();
		BikeSubscriber<ClientSession> sub;
		
		client.startSession().subscribe(sub = new BikeSubscriber<>());
		
		ClientSession session = sub.getSingleResult();
		session.startTransaction();
		
		try {
			productDao.updateQuantity(clientSession, productId, 2);
			orderDao.deleteOrder(clientSession, orderId, productId);
			session.commitTransaction().subscribe(new BikeSubscriber<>());
			return "Transaction complete";
		} catch (Exception e) {
			session.abortTransaction().subscribe(new BikeSubscriber<>());
			e.printStackTrace();
		}finally {
			session.close();
		}
		return "Transaction fail";
	}
}
