package jahv.jpahibernate.ch4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parking_space")
public class ParkingLotEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int lot;
	private String location;

	/**
	 * The value of mappedBy is the name of the attribute in 
	 * the owning entity that points back to the inverse entity.
	 */
	@OneToOne(mappedBy = "parkingSpace")
	private EmployeeV2 employee;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the lot
	 */
	public int getLot() {
		return lot;
	}

	/**
	 * @param lot the lot to set
	 */
	public void setLot(final int lot) {
		this.lot = lot;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

	/**
	 * @return the employee
	 */
	public EmployeeV2 getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(EmployeeV2 employee) {
		this.employee = employee;
	}

}
