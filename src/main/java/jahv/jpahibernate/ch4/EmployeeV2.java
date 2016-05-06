package jahv.jpahibernate.ch4;

import jahv.jpahibernate.ch2.Employee;
import jahv.jpahibernate.utils.EnumValues;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity for table name employee_ch4
 * Since MySql does not support schemas, I'm setting schema value as an empty string
 * Since the catalog is being specified in URL connection I'm setting this as empty string
 * 
 * catalog, schema and name give 3 part names for tables:
 * 
 * 		catalog.schema.table for example companyDB.dbo.Users
 * 
 * Default access set to FIELD
 * 
 * @author jose.hernandez
 * @since April 12, 2016
 *
 */
@Entity
@Table(catalog = "", schema = "", name = "employee_ch4")
@Access(value = AccessType.FIELD)
public class EmployeeV2 {

	/**
	 * GenerationType.AUTO & GenerationType.IDENTITY need to add auto increment in DB
	 * GenerationType.TABLE need @TableGenerator annotation
	 * GenerationType.SEQUENCE need @SequenceGenerator annotation
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// @TableGenerator(name = "EmployeeV2_Generator", table = "employeev2_id_generator", pkColumnName =
	// "generator_name",
	// pkColumnValue = "EmployeeV2", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	// @GeneratedValue(generator = "EmployeeV2_Generator", strategy = GenerationType.TABLE)
	@Column(name = "user_id")
	private int id;

	@Column(name = "user_name")
	private String name;

	@Column(name = "double_data")
	private Double doubleData;

	@Column(name = "boolean_data")
	private boolean booleanData;

	@Column(name = "array_data")
	private String[] arrayData;

	// Not necessary @Column since is the same name
	@Lob
	private byte[] array_data_2;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time_data")
	private Date dateTimeData;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_data")
	private Date dateData;

	@Temporal(TemporalType.TIME)
	private Date time_data;

	@Column(name = "enum_data")
	private EnumValues enumData;

	@Column(name = "enum_data_2")
	@Enumerated(EnumType.STRING)
	private EnumValues enumData2;

	@Column(name = "serializable_data")
	@Basic(fetch = FetchType.LAZY)
	@Lob
	private Employee serializableEmployeeData;

	/**
	 * Join column is needed when the fk column names different to department_id.
	 * The value is the actual column name in DB
	 */
	@ManyToOne
	@JoinColumn(name = "depto_id")
	private DepartmentEntity department;

	@OneToOne
	@JoinColumn(name = "parking_lot")
	private ParkingLotEntity parkingSpace;

	@ManyToMany
	@JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> projects;
	/**
	 * @return the doubleData
	 */
	public Double getDoubleData() {
		return doubleData;
	}

	/**
	 * @param doubleData the doubleData to set
	 */
	public void setDoubleData(final Double doubleData) {
		this.doubleData = doubleData;
	}

	/**
	 * @return the booleanData
	 */
	public boolean isBooleanData() {
		return booleanData;
	}

	/**
	 * @param booleanData the booleanData to set
	 */
	public void setBooleanData(final boolean booleanData) {
		this.booleanData = booleanData;
	}

	/**
	 * @return the arrayData
	 */
	public String[] getArrayData() {
		return arrayData;
	}

	/**
	 * @param arrayData the arrayData to set
	 */
	public void setArrayData(final String[] arrayData) {
		this.arrayData = arrayData;
	}

	/**
	 * @return the dateTimeData
	 */
	public Date getDateTimeData() {
		return dateTimeData;
	}

	/**
	 * @param dateTimeData the dateTimeData to set
	 */
	public void setDateTimeData(final Date dateTimeData) {
		this.dateTimeData = dateTimeData;
	}

	/**
	 * @return the dateData
	 */
	public Date getDateData() {
		return dateData;
	}

	/**
	 * @param dateData the dateData to set
	 */
	public void setDateData(final Date dateData) {
		this.dateData = dateData;
	}

	/**
	 * @return the enumData
	 */
	public EnumValues getEnumData() {
		return enumData;
	}

	/**
	 * @param enumData the enumData to set
	 */
	public void setEnumData(final EnumValues enumData) {
		this.enumData = enumData;
	}

	/**
	 * @return the serializableEmployeeData
	 */
	public Employee getSerializableEmployeeData() {
		return serializableEmployeeData;
	}

	/**
	 * @param serializableEmployeeData the serializableEmployeeData to set
	 */
	public void setSerializableEmployeeData(final Employee serializableEmployeeData) {
		this.serializableEmployeeData = serializableEmployeeData;
	}

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
	 * @return the array_data_2
	 */
	public byte[] getArray_data_2() {
		return array_data_2;
	}

	/**
	 * @param array_data_2 the array_data_2 to set
	 */
	public void setArray_data_2(final byte[] array_data_2) {
		this.array_data_2 = array_data_2;
	}

	/**
	 * @return the enumData2
	 */
	public EnumValues getEnumData2() {
		return enumData2;
	}

	/**
	 * @param enumData2 the enumData2 to set
	 */
	public void setEnumData2(final EnumValues enumData2) {
		this.enumData2 = enumData2;
	}

	/**
	 * @return the time_data
	 */
	public Date getTime_data() {
		return time_data;
	}

	/**
	 * @param time_data the time_data to set
	 */
	public void setTime_data(final Date time_data) {
		this.time_data = time_data;
	}

	/**
	 * @return the department
	 */
	public DepartmentEntity getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(final DepartmentEntity department) {
		this.department = department;
	}

	/**
	 * @return the parkingSpace
	 */
	public ParkingLotEntity getParkingSpace() {
		return parkingSpace;
	}

	/**
	 * @param parkingSpace the parkingSpace to set
	 */
	public void setParkingSpace(final ParkingLotEntity parkingSpace) {
		this.parkingSpace = parkingSpace;
	}

	/**
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(final List<Project> projects) {
		this.projects = projects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmployeeV2 [id=" + id + ", name=" + name + ", department=" + department + ", parkingSpace="
				+ parkingSpace + "]";
	}

}
