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
		id = employee.getId();
		firstName = employee.getFirstName();
		lastName = employee.getLastName();
		phone = employee.getPhone();
		jobTitle = employee.getJobTitle();
	}
}
