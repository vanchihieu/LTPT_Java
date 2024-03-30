package entity;

public class OrderDetail {
	private int quantity;
	private String color;
	private double price;
	private double discount;
	private double lineTotal;
	private Product product;
	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}
	public OrderDetail(int quantity, String color, double price, double discount, double lineTotal, Product product) {
		super();
		this.quantity = quantity;
		this.color = color;
		this.price = price;
		this.discount = discount;
		this.lineTotal = quantity * price * (1-discount);
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getLineTotal() {
		return lineTotal;
	}
	public void setLineTotal(double lineTotal) {
		this.lineTotal = lineTotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "OrderDetail [quantity=" + quantity + ", color=" + color + ", price=" + price + ", discount=" + discount
				+ ", lineTotal=" + lineTotal + ", product=" + product + "]";
	}
	
	
	
}
