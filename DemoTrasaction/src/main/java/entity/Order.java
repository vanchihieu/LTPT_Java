package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class Order {

    private ObjectId id;
    private Date orderDate;
    private OrderStatus orderStatus;
    private Date shippedDate;
    private Customer customer;
    private Staff staff;
    private double orderTotal;
    private Address shippingAddress;
    private List<OrderDetail> orderDetails;
    
    public Order() {
        this(new Date(), OrderStatus.NEW);
    }

    public Order(Date shippedDate, OrderStatus orderStatus){
        this.orderDate = new Date();
        this.orderStatus = OrderStatus.NEW;
        this.shippedDate = new Date();
        this.orderTotal = 0;
        this.orderDetails = new ArrayList<>();
    }
    
    public void addOrderDetail(OrderDetail orderDetail) {
        this.orderDetails.add(orderDetail);
        this.orderTotal += orderDetail.getLineTotal();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void updateOrderTotal() {
        double total = 0;
        for (OrderDetail orderDetail : orderDetails) {
            total += orderDetail.getLineTotal();
        }
        this.orderTotal = total;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }        
}
