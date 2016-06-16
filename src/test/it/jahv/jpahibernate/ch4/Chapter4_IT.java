package jahv.jpahibernate.ch4;

import jahv.jpahibernate.ch2.Employee;
import jahv.jpahibernate.utils.EnumValues;
import jahv.jpahibernate.utils.GenericRepository;
import jahv.jpahibernate.utils.Utils;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test class for {@link EmployeeRepositoryV2}
 * 
 * @author jose.hernandez
 * @since 12 April, 2016
 */
public class Chapter4_IT {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EmployeeRepositoryV2 employeeRepositoryV2;

	private static GenericRepository<EmployeeV2> employeeRepo;
	private static GenericRepository<DepartmentEntity> deparmentRepo;
	private static GenericRepository<ParkingLotEntity> parkingRepo;
	private static GenericRepository<Project> projectRepo;

	/**
	 * Initialize {@link EntityManagerFactory}, {@link EntityManager} and {@link EmployeeRepositoryV2}
	 * to be used in test cases
	 * 
	 * @author jose.hernandez
	 * @since 12 April, 2016
	 */
	@BeforeClass
	public static void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeServiceUnit");
		entityManager = entityManagerFactory.createEntityManager();
		employeeRepositoryV2 = new EmployeeRepositoryV2(entityManager);

		employeeRepo = new GenericRepository<EmployeeV2>(entityManager);
		deparmentRepo = new GenericRepository<DepartmentEntity>(entityManager);
		parkingRepo = new GenericRepository<ParkingLotEntity>(entityManager);
		projectRepo = new GenericRepository<Project>(entityManager);
	}

	/**
	 * Close {@link EntityManager} and {@link EntityManagerFactory} connections
	 * 
	 * @author jose.hernandez
	 * @since 12 April, 2016
	 */
	@AfterClass
	public static void tearDown() {
		entityManager.close();
		entityManagerFactory.close();
	}

	/**
	 * Test for save and find methods
	 * when the {@link EmployeeV2} id is no generated
	 * 
	 * @author jose.hernandez
	 * @since 12 April, 2016
	 */
	@Ignore(value = "Since DB has changed to have an auto increment id generation")
	@Test
	public void testSaveMethod_NO_ID_GENERATED() {
		final int id = Utils.generateIdBasedOnTime();
		final String name = "jahv - " + id;

		final EmployeeV2 employeeV2 = mockEmploeeV2(name);
		employeeV2.setId(id);

		final EmployeeV2 employeeV2Saved = employeeRepositoryV2.saveEmployee(employeeV2);
		Assertions.assertThat(employeeV2Saved).isNotNull();

		final EmployeeV2 employeeV2Found = employeeRepositoryV2.findEmployee(id);
		Assertions.assertThat(employeeV2Found).isNotNull();
	}

	/**
	 * Test for save and find methods when the {@link EmployeeV2} id is GeneratedValue(strategy = GenerationType.AUTO)
	 * 
	 * @author jose.hernandez
	 * @since 12 April, 2016
	 */
	@Test
	public void testSaveMethod_ID_Auto() {
		final int id = Utils.generateIdBasedOnTime();
		final String name = "jahv - AUTO - " + id;
		final EmployeeV2 employeeV2 = mockEmploeeV2(name);

		final Address address = new Address();
		address.setCity("Coyoacan");
		address.setState("DF");
		address.setStreet("Cicalco 23");
		address.setZipCode("04369");

		employeeV2.setAddress(address);

		final EmployeeV2 savedEmployeeV2 = employeeRepositoryV2.saveEmployee(employeeV2);
		Assertions.assertThat(savedEmployeeV2.getId()).isNotNull();

		final EmployeeV2 employeeV2Found = employeeRepositoryV2.findEmployee(savedEmployeeV2.getId());
		Assertions.assertThat(employeeV2Found).isNotNull();
	}

	@Test
	public void findEmployee() {
		final EmployeeV2 employeeV2_RH = employeeRepositoryV2.findEmployee(1);
		Assertions.assertThat(employeeV2_RH).isNotNull();
		Assertions.assertThat(employeeV2_RH.getDepartment()).isNotNull();
		Assertions.assertThat(employeeV2_RH.getDepartment().getName()).isEqualTo("Development - Eugenia");
		Assertions.assertThat(employeeV2_RH.getProjects()).isNotNull().isNotEmpty();

		final EmployeeV2 employeeV2_Dev = employeeRepositoryV2.findEmployee(2);
		Assertions.assertThat(employeeV2_Dev).isNotNull();
		Assertions.assertThat(employeeV2_Dev.getDepartment()).isNotNull();
		Assertions.assertThat(employeeV2_Dev.getDepartment().getName()).isEqualTo("Development - Eugenia");
	}

	/**
	 * Test saving an employee with its corresponding department and parking lot
	 */
	@Test
	public void testSavingEmployeeAndItsRelations() {
		final DepartmentEntity department = new DepartmentEntity();
		department.setName("Development - Eugenia");
		deparmentRepo.save(department);

		final ParkingLotEntity parkingLot = new ParkingLotEntity();
		parkingLot.setLocation("Tower 3");
		parkingLot.setLot(789);
		parkingRepo.save(parkingLot);

		final EmployeeV2 employee = new EmployeeV2();
		employee.setName("Jose Antonio");
		employee.setDepartment(department);
		employee.setParkingSpace(parkingLot);
		
		final EmployeeV2 employeeSaved = employeeRepo.save(employee);

		final EmployeeV2 employeeFound = employeeRepo.find(EmployeeV2.class, employeeSaved.getId());

		Assertions.assertThat(employeeFound).isNotNull();
		Assertions.assertThat(employeeFound.getDepartment()).isNotNull();
		Assertions.assertThat(employeeFound.getParkingSpace()).isNotNull();

	}

	/**
	 * Test saving an employee with its corresponding department and parking lot
	 */
	@Test
	public void testFindParkingSpace() {

		final ParkingLotEntity parkingLot = parkingRepo.find(ParkingLotEntity.class, 1);

		Assertions.assertThat(parkingLot).isNotNull();
		Assertions.assertThat(parkingLot.getEmployee()).isNotNull();
	}

	/**
	 * Test saving an employee with its corresponding department and parking lot
	 */
	@Test
	public void testFindDepartment() {

		final DepartmentEntity department = deparmentRepo.find(DepartmentEntity.class, 5L);

		Assertions.assertThat(department).isNotNull();
		Assertions.assertThat(department.getEmployees()).isNotNull().isNotEmpty();
	}

	@Test
	public void testFindProject() {
		final Project project = projectRepo.find(Project.class, 2);

		Assertions.assertThat(project).isNotNull();
		Assertions.assertThat(project.getEmployees()).isNotNull().isNotEmpty();
	}

	/**
	 * MEthod to create a employeeV2 object
	 * @param name
	 * @return
	 */
	private EmployeeV2 mockEmploeeV2(final String name) {
		final EmployeeV2 employeeV2 = new EmployeeV2();
		employeeV2.setName(name);
		employeeV2.setDoubleData(1.2);
		employeeV2.setBooleanData(true);
		employeeV2.setArrayData(new String[] { "a", "b", "c" });
		employeeV2.setDateTimeData(Calendar.getInstance().getTime());
		employeeV2.setDateData(new Date());
		employeeV2.setTime_data(new Date());
		employeeV2.setEnumData(EnumValues.D);
		employeeV2.setEnumData2(EnumValues.D);
		employeeV2.setSerializableEmployeeData(new Employee(name));
		employeeV2.setArray_data_2(new byte[] { 1, 2, 3, 4, 5 });

		return employeeV2;
	}

}
