/**
 * 
 */
package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreCustomer;
import pet.store.service.StoreService;

/**
 * 
 */
@RestController
@RequestMapping("/pet_store")
@Slf4j
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	@PostMapping("/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer insertCustomer(@RequestBody PetStoreCustomer customerData) {
		log.info("Creating customer a{}", customerData);
		return storeService.saveCustomer(customerData);
	}
			
}
