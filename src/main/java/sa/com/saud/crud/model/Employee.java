package sa.com.saud.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author salghamdi
 *
 */
@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private int employeeID;

	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	@Column(name="city")
	private String city;
	@Column(name="salary")
	private String salary;
	
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
		
	
	
	
	
	
}
