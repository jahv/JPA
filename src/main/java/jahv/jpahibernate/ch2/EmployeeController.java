package jahv.jpahibernate.ch2;

import jahv.jpahibernate.utils.Utils;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Main class
 * @author jose.hernandez
 *
 */
public class EmployeeController {

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) {
		System.out.println("Running: " + EmployeeController.class);
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeServiceUnit");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EmployeeRepository employeeRepository = new EmployeeRepository(entityManager);

		final int id = Utils.generateIdBasedOnTime();

		// Create and saves employee
		final Employee employeeToSave = new Employee(id);
		employeeToSave.setName("Jose");
		employeeToSave.setSalary(100);
		employeeToSave.setPhoneNumber("7");
		Employee employee = employeeRepository.saveEmployee(employeeToSave);
		System.out.println("Saved: " + employee);

		// Not found employee
		employee = employeeRepository.findEmployee(-1);
		System.out.println("Employee found: " + employee);

		// Find a specific employee
		employee = employeeRepository.findEmployee(id);
		System.out.println("Employee found: " + employee);

		// Find all employees
		final List<Employee> employees = employeeRepository.findAllEmployees();
		System.out.println("Employees found: " + employees);

		// Update employee
		employee = employeeRepository.raiseSalary(id, 1);
		System.out.println("Employee updated: " + employee);

		// Remove employee
		// employeeRepository.deleteEmployee(id);
		System.out.println("Removed employee: " + id);

		// Closing entityManager and entityManagerFactory
		entityManager.close();
		entityManagerFactory.close();

	}

}
