package entity;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8725169730004342142L;
	@Id
	protected String id;
	protected String firstName;
	protected String lastName;
	protected String phone;
	
	public Person() {
	}
	
}
