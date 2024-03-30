package entity;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Hotel {
	@BsonId
	private String hotelId;
	@BsonProperty("hotel_name")
	private String hotelName;
	private String category;
	private double rating;
	private Address address;
	private List<Room> rooms;
	public Hotel() {
		// TODO Auto-generated constructor stub
	}
	public Hotel(String hotelId, String hotelName, String category, double rating, Address address, List<Room> rooms) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.category = category;
		this.rating = rating;
		this.address = address;
		this.rooms = rooms;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", category=" + category + ", rating="
				+ rating + ", address=" + address + ", rooms=" + rooms + "]";
	}
	
}
