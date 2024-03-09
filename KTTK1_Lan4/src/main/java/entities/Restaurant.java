package entities;

import java.util.List;

public class Restaurant {
	private long restaurantId;
	private String name;
	private String borough;
	private String cuisine;
	private boolean isActive;
	private List<String> categories;
	private Address address;
	private List<Grade> grades;

	public Restaurant() {
	}

	public Restaurant(String name, String borough) {
		this.name = name;
		this.borough = borough;
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Restaurant(long restaurantId, String name, String borough, String cuisine, boolean isActive,
			List<String> categories, Address address, List<Grade> grades) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.borough = borough;
		this.cuisine = cuisine;
		this.isActive = isActive;
		this.categories = categories;
		this.address = address;
		this.grades = grades;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBorough() {
		return borough;
	}

	public void setBorough(String borough) {
		this.borough = borough;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public Address getAddress() {
		if (address == null) {
			address = new Address();
		}
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", borough=" + borough + ", cuisine="
				+ cuisine + ", isActive=" + isActive + ", categories=" + categories + ", address=" + address
				+ ", grades=" + grades + "]";
	}

	public void setCoord(List<Double> parseDoubleArray) {
		// TODO Auto-generated method stub

	}

}
