package entities;

import java.util.List;

public class Address {
	private String building;
	private List<Float> coord;
	private String street;
	private String zipcode;

	public Address() {
	}

	public Address(String building, List<Float> coord, String street, String zipcode) {
		super();
		this.building = building;
		this.coord = coord;
		this.street = street;
		this.zipcode = zipcode;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public List<Float> getCoord() {
		return coord;
	}

	public void setCoord(List<Float> coord) {
		this.coord = coord;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Address [building=" + building + ", coord=" + coord + ", street=" + street + ", zipcode=" + zipcode
				+ "]";
	}

}
