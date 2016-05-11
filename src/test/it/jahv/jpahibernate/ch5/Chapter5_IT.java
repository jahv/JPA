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
	private static GenericRepository<EmployeeCh5> employeeCh5Repository;

	@BeforeClass
	public static void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeServiceUnit");
		entityManager = entityManagerFactory.createEntityManager();
		employeeCh5Repository = new GenericRepository<EmployeeCh5>(entityManager);
	}

	@Test
	public void saveFindTest() {
		final EmployeeCh5 employeeCh5 = new EmployeeCh5();
		employeeCh5.setName("Jose Antonio " + new Date());
		employeeCh5.setSalary(Math.random() * 1000);

		final EmployeeCh5 employeeCh5Saved = employeeCh5Repository.save(employeeCh5);

		Assertions.assertThat(employeeCh5Saved).isNotNull();
		Assertions.assertThat(employeeCh5Saved.getId()).isNotNull();

		final EmployeeCh5 employeeCh5Found = employeeCh5Repository.find(EmployeeCh5.class, employeeCh5Saved.getId());

		Assertions.assertThat(employeeCh5Found).isEqualTo(employeeCh5Saved);
	}

	@Test
	public void findTest() {
		final EmployeeCh5 employeeCh5Found = employeeCh5Repository.find(EmployeeCh5.class, 1L);
		Assertions.assertThat(employeeCh5Found).isNotNull();
		Assertions.assertThat(employeeCh5Found.getNickNames()).isNotNull().isNotEmpty();
		Assertions.assertThat(employeeCh5Found.getVacationBooking()).isNotNull().isNotEmpty();
	}
}
