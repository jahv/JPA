package jahv.jpahibernate.ch5;

import jahv.jpahibernate.utils.GenericRepository;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;


public class Chapter5_IT {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static GenericRepository<EmployeeCh5Entity> employeeCh5Repository;
	private static GenericRepository<DepartmentCh5Entity> departmentCh5Repository;

	/**
	 * Initialize entity manager and repositories
	 */
	@BeforeClass
	public static void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeServiceUnit");
		entityManager = entityManagerFactory.createEntityManager();
		employeeCh5Repository = new GenericRepository<EmployeeCh5Entity>(entityManager);
		departmentCh5Repository = new GenericRepository<DepartmentCh5Entity>(entityManager);
	}

	/**
	 * Test to save & find {@link EmployeeCh5Entity}
	 */
	@Test
	public void saveFindTest() {
		final EmployeeCh5Entity employeeCh5Entity = new EmployeeCh5Entity();
		employeeCh5Entity.setNameEmployee("Jose Antonio " + new Date());
		employeeCh5Entity.setSalary(Math.random() * 1000);

		final EmployeeCh5Entity employeeCh5Saved = employeeCh5Repository.save(employeeCh5Entity);

		Assertions.assertThat(employeeCh5Saved).isNotNull();
		Assertions.assertThat(employeeCh5Saved.getId()).isNotNull();

		final EmployeeCh5Entity employeeCh5Found = employeeCh5Repository.find(EmployeeCh5Entity.class, employeeCh5Saved.getId());

		Assertions.assertThat(employeeCh5Found).isEqualTo(employeeCh5Saved);
	}

	/**
	 * Test to verify {@link EmployeeCh5Entity} and its {@link VacationEntryEmbeddable} and nick names
	 */
	@Test
	public void findEmployeeTest() {
		final EmployeeCh5Entity employeeCh5Found = employeeCh5Repository.find(EmployeeCh5Entity.class, 1L);
		Assertions.assertThat(employeeCh5Found).isNotNull();
		Assertions.assertThat(employeeCh5Found.getNickNames()).isNotNull().isNotEmpty();
		Assertions.assertThat(employeeCh5Found.getVacationBooking()).isNotNull().isNotEmpty();
		Assertions.assertThat(employeeCh5Found.getDepto()).isNotNull();
		Assertions.assertThat(employeeCh5Found.getPhones()).isNotNull().isNotEmpty();
	}

	/**
	 * Test to find all {@link EmployeeCh5Entity} by {@link DepartmentCh5Entity}
	 */
	@Test
	public void findDepartmentTest() {
		final DepartmentCh5Entity departmentCh5Entity = departmentCh5Repository.find(DepartmentCh5Entity.class, 1L);
		Assertions.assertThat(departmentCh5Entity).isNotNull();
		Assertions.assertThat(departmentCh5Entity.getEmployees()).isNotNull().isNotEmpty();
	}
}
