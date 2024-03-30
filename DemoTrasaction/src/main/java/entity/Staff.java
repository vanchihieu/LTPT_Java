package entity;

public class Staff {

    private long id;
    private String firstName;
    private String lastName;
    private Phone phone;
    private String email;
    private Staff manager;

    public Staff() {
    }

    public Staff(long id, String firstName, String lastName, Phone phone, String email, Staff manager) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.manager = manager;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Staff getManager() {
        return manager;
    }

    public void setManager(Staff manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Staff [email=" + email + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName + ", manager="
                + manager + ", phone=" + phone + "]";
    }

}
