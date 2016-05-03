package jahv.jpahibernate.ch4;

import javax.persistence.EntityManager;

/**
 * Service class for {@link EmployeeV2} entity
 * 
 * @author jose.hernandez
 * @since April 12th, 2016
 *
 */
public class EmployeeRepositoryV2 {

	private EntityManager entityManager;

	/**
	 * Constructor
	 * 
	 * @param entityManager
	 */
	public EmployeeRepositoryV2(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Instantiate and save {@link EmployeeV2} to DB
	 * 
	 * @param employee
	 * @return {@link EmployeeV2}
	 */
	public EmployeeV2 saveEmployee(final EmployeeV2 employee) {
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		return employee;
	}

	/**
	 * Find {@link EmployeeV2} based on id
	 * 
	 * @param id
	 * @return {@link EmployeeV2}
	 */
	public EmployeeV2 findEmployee(final int id) {
		return entityManager.find(EmployeeV2.class, id);
	}

}
