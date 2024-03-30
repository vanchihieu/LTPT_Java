package entity;
//{
//	  _id: ObjectId("5c8eccc1caa187d17ca6ed19"),
//	  city: 'BAILEYTON',
//	  zip: '35019',
//	  loc: { y: 34.268298, x: 86.621299 },
//	  pop: 1781,
//	  state: 'AL'
//	}

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Zip {
	@BsonId
	private ObjectId id;
	private String city;
	private String zip;
	@BsonProperty("loc")
	private Location location;
	private int pop;
	private String state;
	
	public Zip() {
	}

	public Zip(ObjectId id, String city, String zip, Location location, int pop, String state) {
		super();
		this.id = id;
		this.city = city;
		this.zip = zip;
		this.location = location;
		this.pop = pop;
		this.state = state;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getPop() {
		return pop;
	}

	public void setPop(int pop) {
		this.pop = pop;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Zip [id=" + id + ", city=" + city + ", zip=" + zip + ", location=" + location + ", pop=" + pop
				+ ", state=" + state + "]";
	}
	
	
	
}
