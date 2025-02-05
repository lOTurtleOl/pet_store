/**
 * 
 */
package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.service.PetStoreService;

/**
 * 
 */
@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	
	@Autowired
	private PetStoreService petStoreService;
	
	// PetStore Methods
	
	@PostMapping("/pet_store")
	@ResponseStatus(code = HttpStatus.CREATED)	
	public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creating pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);		
		log.info("Updating pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@GetMapping
	public List<PetStoreData> getAllPetStores() {
		return petStoreService.retrieveAllPetStores();
	}
	
	@GetMapping("/{petStoreId}")
	public PetStoreData getPetStoreById(@PathVariable Long petStoreId) {
		log.info("Retrieving pet store with ID: {}", petStoreId);
		return petStoreService.getPetStoreById(petStoreId);
	}
	
	@DeleteMapping("/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		log.info("Deleting pet store with ID: {}", petStoreId);
		petStoreService.deletePetStoreById(petStoreId);
		
		return Map.of("message", "Pet store with ID " + petStoreId + " deleted successfully.");
	}
	
	// Customer Methods
	
	@PostMapping("{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer insertCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer customerData) {
		log.info("Creating customer {}", customerData);
		return petStoreService.saveCustomer(petStoreId, customerData);
	}
		
	// Employee Methods
	
	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee insertEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee employeeData) {
		log.info("Creating employee {}", employeeData);
		return petStoreService.saveEmployee(petStoreId, employeeData);
	}
}
