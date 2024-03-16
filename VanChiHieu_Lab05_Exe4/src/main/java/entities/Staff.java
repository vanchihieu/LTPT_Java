package entities;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import com.google.gson.annotations.SerializedName;

public class Staff {
	@SerializedName("_id")
	private long staffId;
	@SerializedName("first_name")
	private String firstName;
	@SerializedName("last_name")
	private String lastName;
	private Phone phone;
	private String email;
	private Staff manager;

	public Staff(long staffId) {
		this.staffId = staffId;
	}

	public Staff() {
	}

	public Staff(long staffId, String firstName, String lastName, Phone phone, String email, Staff manager) {
		super();
		this.staffId = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.manager = manager;
	}

	public long getStaffId() {
		return staffId;
	}

	public void setStaffId(long staffId) {
		this.staffId = staffId;
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
		return "Staff [staffId=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", email=" + email + ", manager=" + manager + "]";
	}

}
