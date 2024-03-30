package entity;

public class OrderDetail {
    private Product product;
    private int quantity;
    private String color;
    private double price;
    private double discount;
    private double lineTotal;

    public OrderDetail() {
    }

    public OrderDetail(Product product, int quantity, String color, double price, double discount) {
        this.product = product;
        this.quantity = quantity;
        this.color = color;
        this.price = price;
        this.discount = discount;
        this.lineTotal = price * quantity * (1 - discount);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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

    @Override
    public String toString() {
        return "OrderDetail [color=" + color + ", discount=" + discount + ", lineTotal=" + lineTotal + ", price=" + price
                + ", product=" + product + ", quantity=" + quantity + "]";
    }

}
