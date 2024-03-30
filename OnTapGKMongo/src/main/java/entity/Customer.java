package entity;

import java.util.Date;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Customer {
	@BsonId
	private String customerId;
	@BsonProperty("first_name")
	private String firstName;
	@BsonProperty("last_name")
	private String lastName;
	private String email;
	@BsonProperty("registration_date")
	private Date registrationDate;
	private List<Phone> phones;
	private Address address;
	
	public Customer() {
	}
	
	public Customer(String customerId, String firstName, String lastName, String email, Date registrationDate,
			List<Phone> phones, Address address) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.registrationDate = registrationDate;
		this.phones = phones;
		this.address = address;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", registrationDate=" + registrationDate + ", phones=" + phones + ", address=" + address
				+ "]";
	}
	
}
