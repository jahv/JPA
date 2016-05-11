package jahv.jpahibernate.ch5;

import java.util.List;
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
import javax.persistence.Table;

import com.google.common.base.Objects;

@Entity
@Table(name = "Employee_chapter5")
public class EmployeeCh5 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "INT")
	private Long id;

	@Column(name = "NAME", columnDefinition = "VARCHAR", length = 50)
	private String name;

	@Column(name = "SALARY", columnDefinition = "DOUBLE")
	private Double salary;

	@ElementCollection(targetClass = VacationEntry.class, fetch = FetchType.LAZY)
	@CollectionTable(name = "ch5_employee_vacations", joinColumns = @JoinColumn(name = "employeeId"))
	@AttributeOverride(name = "daysTaken", column = @Column(name = "days"))
	private List<VacationEntry> vacationBooking;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "ch5_nicknames", joinColumns = @JoinColumn(name = "employeeId"))
	@Column(name = "use")
	private Set<String> nickNames;

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
	public List<VacationEntry> getVacationBooking() {
		return vacationBooking;
	}

	/**
	 * @param vacationBooking the vacationBooking to set
	 */
	public void setVacationBooking(final List<VacationEntry> vacationBooking) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmployeeCh5 [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, salary);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof EmployeeCh5) {
			final EmployeeCh5 that = (EmployeeCh5) obj;
			return Objects.equal(this.id, that.id)
					&& Objects.equal(this.name, that.name)
					&& Objects.equal(this.salary, that.salary);
		}
		return false;
	}

}
