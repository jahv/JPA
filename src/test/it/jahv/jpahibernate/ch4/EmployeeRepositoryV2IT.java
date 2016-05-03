package jahv.jpahibernate.ch4;

import jahv.jpahibernate.ch2.Employee;
import jahv.jpahibernate.utils.EnumValues;
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
public class EmployeeRepositoryV2IT {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EmployeeRepositoryV2 employeeRepositoryV2;

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
		Assertions.assertThat(employeeV2_RH.getDepartment().getName()).isEqualTo("RH");

		final EmployeeV2 employeeV2_Dev = employeeRepositoryV2.findEmployee(2);
		Assertions.assertThat(employeeV2_Dev).isNotNull();
		Assertions.assertThat(employeeV2_Dev.getDepartment()).isNotNull();
		Assertions.assertThat(employeeV2_Dev.getDepartment().getName()).isEqualTo("Development");
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
