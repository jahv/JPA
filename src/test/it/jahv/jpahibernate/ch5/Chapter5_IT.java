package jahv.jpahibernate.ch5;

import jahv.jpahibernate.utils.GenericRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		final DepartmentCh5Entity depto = new DepartmentCh5Entity();
		depto.setName("Automation");
		final DepartmentCh5Entity savedDepto = departmentCh5Repository.save(depto);

		final VacationEntryEmbeddable period1 = new VacationEntryEmbeddable();
		period1.setStartDate(new Date());
		period1.setDaysTaken(7);

		final VacationEntryEmbeddable period2 = new VacationEntryEmbeddable();
		final Calendar oneYearLater = Calendar.getInstance();
		oneYearLater.add(Calendar.YEAR, 1);
		period2.setStartDate(oneYearLater.getTime());
		period2.setDaysTaken(15);

		final List<VacationEntryEmbeddable> vacations = new ArrayList<VacationEntryEmbeddable>();
		vacations.add(period1);
		vacations.add(period2);

		final Set<String> nickNames = new HashSet<String>();
		nickNames.add("Tester");
		nickNames.add("Automation Tester");

		final Map<PhoneTypeEnum, String> phones = new HashMap<PhoneTypeEnum, String>();
		phones.put(PhoneTypeEnum.MOBILE, "045 22 23 40 58 38");
		phones.put(PhoneTypeEnum.HOME, "55 75 74 40 85");
		phones.put(PhoneTypeEnum.WORK, "55 26 26 82 82");

		final EmployeeCh5Entity employeeCh5Entity = new EmployeeCh5Entity();
		employeeCh5Entity.setNameEmployee("Jose Antonio " + new Date());
		employeeCh5Entity.setSalary(Math.random() * 1000);

		employeeCh5Entity.setDepto(savedDepto);
		employeeCh5Entity.setVacationBooking(vacations);
		employeeCh5Entity.setNickNames(nickNames);
		employeeCh5Entity.setPhones(phones);

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
