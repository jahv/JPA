package jahv.jpahibernate.ch5;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ch5_department")
public class DepartmentCh5Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT")
	private Long id;

	@Column(columnDefinition = "VARCHAR")
	private String name;

	// One to many, bidirectional relation. Needs the corresponding @ManyToOne in the other side
	// mappedBy: name of attribute in class EmployeeCh5Entity which corresponds this Collection
	@OneToMany(mappedBy = "depto")
	private List<EmployeeCh5Entity> employees;

	// One to many, unidirectional relation.
	// name: Table name for the relation, in this case: Department 1..* Employee
	// joinColumns: column name for joining in the table of "name", in this case table Employee has the column deptoId
	// inverseJoinColumns: column name for joining in the current table, in this case table Department has the column id
	// @OneToMany
	// @JoinTable(name = "ch5_employee", joinColumns = @JoinColumn(name = "deptoId"),
	// inverseJoinColumns = @JoinColumn(name = "id"))
	// private List<EmployeeCh5Entity> employees;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the employees
	 */
	public List<EmployeeCh5Entity> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(final List<EmployeeCh5Entity> employees) {
		this.employees = employees;
	}

}
