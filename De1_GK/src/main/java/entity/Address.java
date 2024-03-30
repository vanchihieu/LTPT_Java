package entity;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Address {
	@BsonProperty("street_address")
	private String streetAddress;
	private String city;
	@BsonProperty("state_province")
	private String stateProvince;
	private String postalCode;
	private String country;
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	public Address(String streetAddress, String city, String stateProvince, String postalCode, String country) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.stateProvince = stateProvince;
		this.postalCode = postalCode;
		this.country = country;
	}

	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateProvince() {
		return stateProvince;
	}
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [streetAddress=" + streetAddress + ", city=" + city + ", stateProvince=" + stateProvince
				+ ", postalCode=" + postalCode + ", country=" + country + "]";
	}
	
	
}
