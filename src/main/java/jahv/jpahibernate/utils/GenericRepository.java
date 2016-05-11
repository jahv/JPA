package jahv.jpahibernate.utils;

import javax.persistence.EntityManager;

/**
 * Generic repository class for <T> entity
 * 
 * @author jose.hernandez
 * @since May 3rd, 2016
 *
 */
public class GenericRepository<T> {

	private EntityManager entityManager;

	/**
	 * Constructor
	 * 
	 * @param entityManager
	 */
	public GenericRepository(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Instantiate and save <T> object to DB
	 * 
	 * @param t
	 * @return T
	 */
	public T save(final T t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
		return t;
	}

	/**
	 * Find Class<T> object based on id
	 * 
	 * @param id
	 * @return Class<T> obj
	 */
	public T find(final Class<T> clazz, final Object id) {
		entityManager.getTransaction().begin();
		final T t = entityManager.find(clazz, id);
		entityManager.getTransaction().commit();
		return t;
	}

}
