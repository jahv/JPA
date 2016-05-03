package jahv.jpahibernate.ch2;

import jahv.jpahibernate.ch2.Employee;
import jahv.jpahibernate.ch2.EmployeeRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for {@link EmployeeRepository}
 * 
 * @author jose.hernandez
 * @since 11 April, 2016
 */
public class EmployeeRepositoryIT {

	private static final int ID = 999;
	private static final String NAME = "JAHV";
	private static final long SALARY = 1000;

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EmployeeRepository employeeRepository;

	/**
	 * Initialize {@link EntityManagerFactory}, {@link EntityManager} and {@link EmployeeRepository}
	 * to be used in test cases
	 * 
	 * @author jose.hernandez
	 * @since 11 April, 2016
	 */
	@BeforeClass
	public static void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeServiceUnit");
		entityManager = entityManagerFactory.createEntityManager();
		employeeRepository = new EmployeeRepository(entityManager);
	}

	/**
	 * Close {@link EntityManager} and {@link EntityManagerFactory} connections
	 * 
	 * @author jose.hernandez
	 * @since 11 April, 2016
	 */
	@AfterClass
	public static void tearDown() {
		entityManager.close();
		entityManagerFactory.close();
	}

	/**
	 * Persist data for every test
	 * 
	 * @author jose.hernandez
	 * @since 11 April, 2016
	 */
	@Before
	public void insertRecord() {
		employeeRepository.saveEmployee(this.mockEmployee());
	}

	/**
	 * Delete record persisted
	 * 
	 * @author jose.hernandez
	 * @since 11 April, 2016
	 */
	@After
	public void deleteRecord() {
		employeeRepository.deleteEmployee(ID);
	}

	/**
	 * Testing findEmployee method when id is in DB and when it is not
	 * 
	 * @author jose.hernandez
	 * @since 11 April, 2016
	 */
	@Test
	public void testFindEmployee() {
		Employee employee = employeeRepository.findEmployee(ID);
		Assert.assertNotNull(employee);
		Assert.assertEquals(employee, this.mockEmployee());

		employee = employeeRepository.findEmployee(123);
		Assert.assertNull(employee);
	}

	/**
	 * Testing findAllEmployees method 
	 * 
	 * @author jose.hernandez
	 * @since 11 April, 2016
	 */
	@Test
	public void testFindAllEmployees() {
		final Employee employeeExpected = this.mockEmployee();

		final List<Employee> employees = employeeRepository.findAllEmployees();
		Assert.assertNotNull(employees);
		Assert.assertFalse(employees.isEmpty());
		Assert.assertTrue(employees.contains(employeeExpected));
	}

	/**
	 * Mock up for {@link Employee}
	 * @return new {@link Employee} 
	 * 
	 * @author jose.hernandez
	 * @since 11 April, 2016
	 */
	private Employee mockEmployee() {
		final Employee employee = new Employee(ID);
		employee.setName(NAME);
		employee.setSalary(SALARY);
		return employee;
	}

}
