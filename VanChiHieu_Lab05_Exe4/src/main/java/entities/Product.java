package entities;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Product {
	@BsonId
	private long productId;
	@BsonProperty("brand_name")
	private String brand;
	@BsonProperty("category_name")
	private String category;
	@BsonProperty("product_name")
	private String name;
	private List<String> colors;
	@BsonProperty("model_year")
	private int modelYear;
	private double price;

	public Product() {
	}

	public Product(long productId, String brand, String category, String name, List<String> colors, int modelYear,
			double price) {
		super();
		this.productId = productId;
		this.brand = brand;
		this.category = category;
		this.name = name;
		this.colors = colors;
		this.modelYear = modelYear;
		this.price = price;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", brand=" + brand + ", category=" + category + ", name=" + name
				+ ", colors=" + colors + ", modelYear=" + modelYear + ", price=" + price + "]";
	}

}
