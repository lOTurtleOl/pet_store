/**
 * 
 */
package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

/**
 * 
 */
@Data
@NoArgsConstructor
public class PetStoreData {

	private Long petStoreId;
	
	private String petStoreName;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	private String phone;
	
	private Set<PetStoreEmployee> employees = new HashSet<>(); // use this data type to reference DTOs instead of referencing the entity directly. This helps avoid recursion.
	
	private Set<PetStoreCustomer> customers = new HashSet<>();
	
	public PetStoreData(PetStore petStore) { // go through and map all petStoreData fields to their respective entity field values
		petStoreId = petStore.getPetStoreId();
		petStoreName = petStore.getPetStoreName();
		address = petStore.getAddress();
		city = petStore.getCity();
		state = petStore.getState();
		zip = petStore.getZip();
		phone = petStore.getPhone();
		
		if (petStore.getCustomers() != null) {
			for (Customer customer : petStore.getCustomers()) {
				if (customer != null) {
					customers.add(new PetStoreCustomer(customer));
				}
			}
		}
		
		if (petStore.getEmployees() != null) {
			for (Employee employee : petStore.getEmployees()) {
				if (employee != null) {
					employees.add(new PetStoreEmployee(employee));
				}
			}
		}
		
	}
}
