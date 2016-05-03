package jahv.jpahibernate.ch2;

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
public class EmployeeRepository {

	private EntityManager entityManager;

	/**
	 * Constructor
	 * 
	 * @param entityManager
	 */
	public EmployeeRepository(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Instantiate and save {@link Employee} employee to DB
	 * 
	 * @param employee
	 * @return {@link Employee}
	 */
	public Employee saveEmployee(final Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		return employee;
	}

	/**
	 * Find {@link Employee} based on id
	 * 
	 * @param id
	 * @return {@link Employee}
	 */
	public Employee findEmployee(final int id) {
		return entityManager.find(Employee.class, id);
	}

	/**
	 * Retrieves list of all {@link Employee} employees
	 * @return List<Employee>
	 */
	public List<Employee> findAllEmployees() {
		final TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);
		return query.getResultList();
	}

	/**
	 * Delete {@link Employee} employee from DB based on id
	 * 
	 * @param id
	 */
	public void deleteEmployee(final int id) {
		final Employee employee = findEmployee(id);
		if (employee != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(employee);
			entityManager.getTransaction().commit();
		}
	}

	/**
	 * Raise {@link Employee} employee salary based on id
	 * 
	 * @param id
	 * @param raise
	 * @return Employee
	 */
	public Employee raiseSalary(final int id, final long raise) {
		final Employee employee = findEmployee(id);
		if (employee != null) {
			entityManager.getTransaction().begin();
			employee.setSalary(employee.getSalary() + raise);
			entityManager.getTransaction().commit();
		}
		return employee;
	}

}
