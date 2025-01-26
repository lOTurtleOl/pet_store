/**
 * 
 */
package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;

/**
 * 
 */
@Data
@NoArgsConstructor
public class PetStoreCustomer {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;	
	
	public PetStoreCustomer(Customer customer) {
		this.id = customer.getId();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.email = customer.getEmail();
	}
}
