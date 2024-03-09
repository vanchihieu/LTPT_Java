package entities;

public class Address {
	private String streetAddress;
	private String city;
	private String state;
	private int postalCode;

	public Address() {
	}

	public Address(String streetAddress, String city, String state, int postalCode) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
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

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public int getPostalCode() {
		return postalCode;
	}

	@Override
	public String toString() {
		return "Address [streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", postCode="
				+ postalCode + "]";
	}
}
