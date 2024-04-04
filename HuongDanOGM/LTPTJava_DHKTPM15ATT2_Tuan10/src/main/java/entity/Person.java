package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4486499217276415550L;
	@Id
	protected String id;
	protected String name;
	@ElementCollection(fetch = FetchType.EAGER)
	protected Set<String> phones;
	
	public Person() {
	}

	public Person(String id, String name,  Set<String> phones) {
		super();
		this.id = id;
		this.name = name;
		this.phones = phones;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", phones=" + phones + "]";
	}
	
	

}
