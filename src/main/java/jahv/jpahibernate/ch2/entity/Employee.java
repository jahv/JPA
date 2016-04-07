package jahv.jpahibernate.ch2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Employee entity
 * 
 * @author jose.hernandez
 * @since April 4th, 2016
 *
 */
@Entity
public class Employee {

	@Id
	private int id;
	private String name;
	private long salary;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}

}
