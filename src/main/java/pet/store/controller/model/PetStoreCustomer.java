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
		id = customer.getId();
		firstName = customer.getFirstName();
		lastName = customer.getLastName();
		email = customer.getEmail();
	}
}
