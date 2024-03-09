package entities;

import java.util.List;

public class Listing {
	private int id;
	private String listingUrl;
	private String name;
	private String summary;
	private int minimumNights;
	private int maximumNights;
	private int bedrooms;
	private int beds;
	private List<String> amenities;
	private double price;
	private Host host;
	private List<Review> reviews;

	public Listing() {
	}

	public Listing(int id, String listingUrl, String name, String summary, int minimumNights, int maximumNights,
			int bedrooms, int beds, List<String> amenities, double price, Host host, List<Review> reviews) {
		this.id = id;
		this.listingUrl = listingUrl;
		this.name = name;
		this.summary = summary;
		this.minimumNights = minimumNights;
		this.maximumNights = maximumNights;
		this.bedrooms = bedrooms;
		this.beds = beds;
		this.amenities = amenities;
		this.price = price;
		this.host = host;
		this.reviews = reviews;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getListingUrl() {
		return listingUrl;
	}

	public void setListingUrl(String listingUrl) {
		this.listingUrl = listingUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getMinimumNights() {
		return minimumNights;
	}

	public void setMinimumNights(int minimumNights) {
		this.minimumNights = minimumNights;
	}

	public int getMaximumNights() {
		return maximumNights;
	}

	public void setMaximumNights(int maximumNights) {
		this.maximumNights = maximumNights;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Listing [id=" + id + ", listingUrl=" + listingUrl + ", name=" + name + ", summary=" + summary
				+ ", minimumNights=" + minimumNights + ", maximumNights=" + maximumNights + ", bedrooms=" + bedrooms
				+ ", beds=" + beds + ", amenities=" + amenities + ", price=" + price + ", host=" + host + ", reviews="
				+ reviews + "]";
	}

}
