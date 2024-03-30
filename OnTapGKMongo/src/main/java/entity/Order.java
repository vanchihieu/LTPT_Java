package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Order {
	@BsonId
	private ObjectId orderId;
	@BsonProperty("order_date")
	private Date orderDate;
	private Date shippedDate;
	@BsonProperty("order_total")
	private double orderTotal;
	private Customer customer;
	private OrderStatus orderStatus;
	private Staff staff;
	@BsonProperty("order_details")
	private List<OrderDetail> orderDetails;
	
	public Order() {
        this(new Date(), OrderStatus.NEW);
    }

    public Order(Date shippedDate, OrderStatus orderStatus){
        this.orderDate = new Date();
//        this.orderStatus = OrderStatus.NEW;
        this.orderStatus = OrderStatus.NEW;
        this.shippedDate = new Date();
        this.orderTotal = 0;
        this.orderDetails = new ArrayList<>();
    }
    public void addOrderDetail(OrderDetail orderDetail) {
        this.orderDetails.add(orderDetail);
        this.orderTotal += orderDetail.getLineTotal();
    }
    public void updateOrderTotal() {
        double total = 0;
        for (OrderDetail orderDetail : orderDetails) {
            total += orderDetail.getLineTotal();
        }
        this.orderTotal = total;
    }

	public ObjectId getOrderId() {
		return orderId;
	}

	public void setOrderId(ObjectId orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", shippedDate=" + shippedDate
				+ ", orderTotal=" + orderTotal + ", customer=" + customer + ", orderStatus=" + orderStatus + ", staff="
				+ staff + ", orderDetails=" + orderDetails + "]";
	}
	
}
