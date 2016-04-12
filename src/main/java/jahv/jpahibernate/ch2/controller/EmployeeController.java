package jahv.jpahibernate.ch2.controller;

import jahv.jpahibernate.ch2.entity.Employee;
import jahv.jpahibernate.ch2.repository.EmployeeRepository;

import java.text.ParseException;
import java.util.Calendar;
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
	 * Method to generate dynamic ids based on current time
	 * @return generated id
	 * @throws ParseException
	 */
	private static int generateIdBasedOnTime() {
		final Calendar calendar = Calendar.getInstance();
		final int hours = calendar.get(Calendar.HOUR_OF_DAY);
		final int minutes = calendar.get(Calendar.MINUTE);
		final int seconds = calendar.get(Calendar.SECOND);
		final int milliSeconds = calendar.get(Calendar.MILLISECOND);
		final String id = "" + hours + minutes + seconds + milliSeconds;
		return new Integer(id);
	}

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) {
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeServiceUnit");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EmployeeRepository employeeRepository = new EmployeeRepository(entityManager);

		final int id = generateIdBasedOnTime();

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
