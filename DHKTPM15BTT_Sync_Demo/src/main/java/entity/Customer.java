package entity;

import java.util.Date;
import java.util.List;

public class Customer {
	private String customerId;
	private String firstName;
	private String lastName;
	private Address address;
	private Date registrationDate;
	private List<Phone> phones;
	
	
	
	public Customer(String customerId, String firstName, Date registrationDate) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.registrationDate = registrationDate;
	}

	public Customer(String customerId, String firstName, String lastName, Address address, Date registrationDate,
			List<Phone> phones) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.registrationDate = registrationDate;
		this.phones = phones;
	}

	public Customer() {
		super();
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", registrationDate=" + registrationDate + ", phones=" + phones + "]";
	}

}
