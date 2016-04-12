package jahv.jpahibernate.ch2.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Employee entity
 * Define the default access type for entity
 * 
 * @author jose.hernandez
 * @since April 4th, 2016
 *
 */

@Entity
@Access(AccessType.FIELD)
public class Employee {

	private static final String LOCAL_AREA_CODE = "222";

	/**
	 * Annotation can go on getter also
	 */
	@Id
	private int id;
	private String name;
	private long salary;
	/**
	 * Even the field is annotated with transient, it will be persisted
	 * because in getter we're overriding the default AccessType, additionally
	 * We're defining the correct column name.
	 */
	@Transient
	private String phoneNumber;

	/**
	 * Default constructor
	 */
	public Employee() {
	}

	/**
	 * Constructor
	 * @param id
	 */
	public Employee(final int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	// @Id
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the salary
	 */
	public long getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(long salary) {
		this.salary = salary;
	}

	/**
	 * Overrides default access type and set it as PROPERTY
	 * Defining the correct column name
	 * 
	 * @return the phone
	 */
	// @Access(AccessType.PROPERTY)
	@Column(name = "phone")
	public String getPhoneNumber() {
		if (phoneNumber.length() == 10) {
			return phoneNumber;
		}
		return LOCAL_AREA_CODE + phoneNumber;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(Employee.class).add("id", id).add("name", name).add("salary", salary)
				.add("phone", phoneNumber).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, salary);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Employee) {
			final Employee that = (Employee) obj;
			return Objects.equal(this.id, that.id)
					&& Objects.equal(this.name, that.name)
					&& Objects.equal(this.salary, that.salary);
		}
		return false;
	}

}
