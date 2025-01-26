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
	
	private Long zip;
	
	private Long phone;
	
	private Set<PetStoreEmployee> employees = new HashSet<>(); // use this data type to reference DTOs instead of referencing the entity directly. This helps avoid recursion.
	
	private Set<PetStoreCustomer> customers = new HashSet<>();
	
	public PetStoreData(PetStore petStore) { // go through and map all petStoreData fields to their respective entity field values
		this.petStoreId = petStore.getPetStoreId();
		this.petStoreName = petStore.getPetStoreName();
		this.address = petStore.getAddress();
		this.city = petStore.getCity();
		this.state = petStore.getState();
		this.zip = petStore.getZip();
		this.phone = petStore.getPhone();
		
		if (petStore.getEmployees() != null) {
			for (Employee employee : petStore.getEmployees()) {
				if (employee != null) {
					this.employees.add(new PetStoreEmployee(employee));
				}
			}
		}
		
		if (petStore.getCustomers() != null) {
			for (Customer customer : petStore.getCustomers()) {
				if (customer != null) {
					this.customers.add(new PetStoreCustomer(customer));
				}
			}
		}
	}
}
