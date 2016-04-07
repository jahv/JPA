package jahv.jpahibernate.ch2.service;

import jahv.jpahibernate.ch2.entity.Employee;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Service class for {@link Employee} entity
 * 
 * @author jose.hernandez
 * @since April 4th, 2016
 *
 */
public class EmployeeService {

	private EntityManager entityManager;

	/**
	 * Constructor
	 * 
	 * @param entityManager
	 */
	public EmployeeService(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Instantiate and save employee to DB
	 * 
	 * @param id
	 * @param name
	 * @param salary
	 * @return Employee
	 */
	public Employee saveEmployee(final int id, final String name, final long salary) {
		final Employee employee = new Employee(id);
		employee.setName(name);
		employee.setSalary(salary);
		entityManager.persist(employee);
		return employee;
	}

	/**
	 * Find employee based on id
	 * 
	 * @param id
	 * @return Employee
	 */
	public Employee findEmployee(final int id) {
		return entityManager.find(Employee.class, id);
	}

	/**
	 * Retrieves list of all employees
	 * @return List<Employee>
	 */
	public List<Employee> findAllEmployees() {
		final TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);
		return query.getResultList();
	}

	/**
	 * Delete employee from DB based on id
	 * 
	 * @param id
	 */
	public void deleteEmployee(final int id) {
		final Employee employee = findEmployee(id);
		if (employee != null) {
			entityManager.remove(employee);
		}
	}

	/**
	 * Raise employee salary based on id
	 * 
	 * @param id
	 * @param raise
	 * @return Employee
	 */
	public Employee raiseSalary(final int id, final long raise) {
		final Employee employee = findEmployee(id);
		if (employee != null) {
			employee.setSalary(employee.getSalary() + raise);
		}
		return employee;
	}

}
