package entity;

import java.util.Date;
import java.util.List;


public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Date registrationDate;
    private List<Phone> phones;

    public Customer() {
    }

    public Customer(String id, String firstName, String lastName, String email, Address address, Date registrationDate, List<Phone> phones) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.registrationDate = registrationDate;
        this.phones = phones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "Customer [address=" + address + ", email=" + email + ", firstName=" + firstName + ", id=" + id
                + ", lastName=" + lastName + ", phones=" + phones + ", registrationDate=" + registrationDate + "]";
    }
}
