package jahv.jpahibernate.ch2.controller;

import jahv.jpahibernate.ch2.entity.Employee;
import jahv.jpahibernate.ch2.service.EmployeeService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeTest {

	public static void main(String[] args) {
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeService");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EmployeeService employeeService = new EmployeeService(entityManager);

		final int id = 100;

		// Create and saves employee
		entityManager.getTransaction().begin();
		Employee employee = employeeService.saveEmployee(id, "Jose Antonio", 1000);
		entityManager.getTransaction().commit();
		System.out.println("Saved: " + employee);

		// Not found employee
		employee = employeeService.findEmployee(-1);
		System.out.println("Employee found: " + employee);

		// Find a specific employee
		employee = employeeService.findEmployee(id);
		System.out.println("Employee found: " + employee);

		// Find all employees
		final List<Employee> employees = employeeService.findAllEmployees();
		System.out.println("Employees found: " + employees);

		// Update employee
		entityManager.getTransaction().begin();
		employee = employeeService.raiseSalary(id, 1);
		entityManager.getTransaction().commit();
		System.out.println("Employee updated: " + employee);

		// Remove employee
		entityManager.getTransaction().begin();
		employeeService.deleteEmployee(id);
		entityManager.getTransaction().commit();
		System.out.println("Removed employee: " + id);

		// Closing entityManager and entityManagerFactory
		entityManager.close();
		entityManagerFactory.close();

	}

}
