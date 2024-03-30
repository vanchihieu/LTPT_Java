package dao;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.*;

import java.util.List;

import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Hotel;
import entity.Room;

public class HotelDao {
	private MongoCollection<Hotel> hotelColl;
	private MongoCollection<Document> docColl;
	private MongoDatabase db;

	public HotelDao() {
		MongoClient client = Connection.getInstance().getClient();
		db = client.getDatabase("Bac20111041");
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		hotelColl = db.getCollection("hotel", Hotel.class).withCodecRegistry(pojoCodecRegistry);
		docColl = db.getCollection("hotel");
	}

	public boolean addHotel(Hotel hotel) {
		Publisher<InsertOneResult> publisher = hotelColl.insertOne(hotel);
		HotelSubscriber<InsertOneResult> sub = new HotelSubscriber<>();
		publisher.subscribe(sub);
		return sub.getSingleResult().getInsertedId() != null;
	}

	public Hotel getHotelById(String id) {
		Publisher<Hotel> publisher = hotelColl.find(Filters.eq("_id", id));
		HotelSubscriber<Hotel> sub = new HotelSubscriber<>();
		publisher.subscribe(sub);
		return sub.getSingleResult();
	}

	// Thêm một phòng mới vào danh sách các phòng đã có của khách sạn khi biết mã số
	// khách sạn (phòng mói không được trùng)
	// db.hotel.createIndex("")
	// db.hotel.updateOne({hotelId: "H1"}, {$addToSet: {rooms: {roomId: "R1",
	// roomType: "Standard", price: 1000000, status: "Available"}}})
	public boolean addRoom(String hotelId, Room room) {
		List<Room> rooms = getHotelById(hotelId).getRooms();
		System.out.println(rooms);
		if (rooms.contains(room)) {
			return false;
		} else {
			Publisher<UpdateResult> updateOne = docColl.updateOne(Filters.eq("_id", hotelId),
					Document.parse("{$addToSet: {rooms: {description: \"" + room.getDescription()
					+ "\", type: \"" + room.getType() + "\", bed_options: " + room.getBedOptions()
					+ ", sleeps_count: \"" + room.getSleepsCount() + "\",tags:"
					+ room.getTags()+ "\", smoke_allowed: " +room.isSmokeAllowed()+ "\"}}}"));
			HotelSubscriber<UpdateResult> sub = new HotelSubscriber<>();
			updateOne.subscribe(sub);
			return sub.getSingleResult().getModifiedCount() > 0;
		}
	}

}
