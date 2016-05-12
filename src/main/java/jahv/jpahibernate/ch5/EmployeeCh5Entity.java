package jahv.jpahibernate.ch5;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.google.common.base.Objects;

@Entity
@Table(name = "ch5_employee")
public class EmployeeCh5Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "INT")
	private Long id;

	@Column(name = "NAME", columnDefinition = "VARCHAR", length = 50)
	private String nameEmployee;

	@Column(name = "SALARY", columnDefinition = "DOUBLE")
	private Double salary;

	@ElementCollection(targetClass = VacationEntryEmbeddable.class, fetch = FetchType.LAZY)
	@CollectionTable(name = "ch5_employee_vacations", joinColumns = @JoinColumn(name = "employeeId"))
	@AttributeOverride(name = "daysTaken", column = @Column(name = "days"))
	private List<VacationEntryEmbeddable> vacationBooking;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "ch5_nicknames", joinColumns = @JoinColumn(name = "employeeId"))
	@Column(name = "nickName")
	private Set<String> nickNames;

	// Unidirectional and bidirectional does not change
	// @JoinColumn: name of the current column to join with Department
	@ManyToOne
	@JoinColumn(name = "deptoId")
	private DepartmentCh5Entity depto;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "ch5_phones", joinColumns = @JoinColumn(name = "employeeId"))
	@MapKeyColumn(name = "type")
	@Column(name = "val")
	private Map<String, String> phones;

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
	public String getNameEmployee() {
		return nameEmployee;
	}

	/**
	 * @param name the name to set
	 */
	public void setNameEmployee(final String nameEmployee) {
		this.nameEmployee = nameEmployee;
	}

	/**
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(final Double salary) {
		this.salary = salary;
	}

	/**
	 * @return the vacationBooking
	 */
	public List<VacationEntryEmbeddable> getVacationBooking() {
		return vacationBooking;
	}

	/**
	 * @param vacationBooking the vacationBooking to set
	 */
	public void setVacationBooking(final List<VacationEntryEmbeddable> vacationBooking) {
		this.vacationBooking = vacationBooking;
	}

	/**
	 * @return the nickNames
	 */
	public Set<String> getNickNames() {
		return nickNames;
	}

	/**
	 * @param nickNames the nickNames to set
	 */
	public void setNickNames(final Set<String> nickNames) {
		this.nickNames = nickNames;
	}

	/**
	 * @return the depto
	 */
	public DepartmentCh5Entity getDepto() {
		return depto;
	}

	/**
	 * @param depto the depto to set
	 */
	public void setDepto(final DepartmentCh5Entity depto) {
		this.depto = depto;
	}

	/**
	 * @return the phones
	 */
	public Map<String, String> getPhones() {
		return phones;
	}

	/**
	 * @param phones the phones to set
	 */
	public void setPhones(final Map<String, String> phones) {
		this.phones = phones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmployeeCh5Entity [id=" + id + ", name=" + nameEmployee + ", salary=" + salary + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(id, nameEmployee, salary);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof EmployeeCh5Entity) {
			final EmployeeCh5Entity that = (EmployeeCh5Entity) obj;
			return Objects.equal(this.id, that.id)
					&& Objects.equal(this.nameEmployee, that.nameEmployee)
					&& Objects.equal(this.salary, that.salary);
		}
		return false;
	}

}
