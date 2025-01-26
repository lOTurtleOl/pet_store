/**
 * 
 */
package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Employee;

/**
 * 
 */
@Data
@NoArgsConstructor
public class PetStoreEmployee {

	private Long id; 
	
	private String firstName;
	
	private String lastName;
	
	private Long phone;
	
	private String jobTitle;
	
	public PetStoreEmployee(Employee employee) {
		this.id = employee.getId();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.phone = employee.getPhone();
		this.jobTitle = employee.getJobTitle();
	}
}
