package jahv.jpahibernate.ch4;

import jahv.jpahibernate.utils.Utils;

import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Main class
 * @author jose.hernandez
 * @since April 12, 2016
 *
 */
public class EmployeeControllerV2 {

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) {
		System.out.println("Running: " + EmployeeControllerV2.class);
		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeServiceUnit");
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EmployeeRepositoryV2 employeeRepositoryV2 = new EmployeeRepositoryV2(entityManager);

		final int id = Utils.generateIdBasedOnTime();

		// Create and saves employee
		final EmployeeV2 employeeToSave = new EmployeeV2();
		employeeToSave.setId(id);
		employeeToSave.setName("Jose");
		final EmployeeV2 employeeSaved = employeeRepositoryV2.saveEmployee(employeeToSave);
		System.out.println("Saved: " + employeeSaved);

		// Find a specific employee
		final EmployeeV2 employeeFound = employeeRepositoryV2.findEmployee(id);
		System.out.println("Employee found: " + employeeFound);

		// Closing entityManager and entityManagerFactory
		entityManager.close();
		entityManagerFactory.close();
	}

}
