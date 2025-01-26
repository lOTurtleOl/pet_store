/**
 * 
 */
package pet.store.controller.model;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.PetStore;

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
	
	private Set<PetStore> petStores;
	
	
}
