package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClientException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;

import entity.Customer;
import entity.Phone;

public class CustomerDao extends AbstractDao {
	private MongoCollection<Document> customerCollection;

	public CustomerDao(MongoClient client) {
		super(client);
		customerCollection = db.getCollection("customers1");
	}

//	db.customers.find({_id:'20'})
	public Customer getCustomer(String id) {
		try {
			Document doc = new Document("_id", id);
			Document rsDoc = customerCollection.find(doc).first();
			Customer cus = fromDocument(rsDoc);

			return cus;
		} catch (MongoClientException e) {
			e.printStackTrace();
		}
		return null;

	}

	private Customer fromDocument(Document doc) {
		Customer cus = new Customer(doc.getString("_id"), doc.getString("first_name"),
				doc.getDate("registration_date"));

		return cus;
	}

	public int addCustomers(List<Customer> list) {
		List<Document> docs = new ArrayList<Document>();

		list.forEach(cus -> {
			docs.add(toDocument(cus));
		});

		try {

			InsertManyResult rs = customerCollection.insertMany(docs);

			return rs.getInsertedIds().size();
		} catch (MongoClientException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean addCustomer(Customer cus) {

		Document doc = toDocument(cus);
		try {

			InsertOneResult rs = customerCollection.insertOne(doc);

			return rs.getInsertedId() != null ? true : false;
		} catch (MongoClientException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Document toDocument(Customer cus) {

		List<Phone> phs = cus.getPhones();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_id", cus.getCustomerId());
		map.put("first_name", cus.getFirstName());
//		map.put("last_name", cus.getLastName());
		map.put("registration_date", cus.getRegistrationDate());

		if (phs != null) {
			List<Document> phDocs = new ArrayList<Document>();
			phs.forEach(ph -> {
				Document doc = new Document("type", ph.getType()).append("number", ph.getNumber());
				phDocs.add(doc);

			});
			map.put("phones", phDocs);
		}

		Document doc = new Document(map);

		return doc;
	}
}
