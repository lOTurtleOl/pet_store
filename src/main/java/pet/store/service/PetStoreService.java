/**
 * 
 */
package pet.store.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

/**
 * 
 */
@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;
	

	
	/**
	 * @param petStoreData
	 * @return
	 */
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		copyPetStoreFields(petStore, petStoreData);
		PetStore savedPetStore = petStoreDao.save(petStore);
		return new PetStoreData(savedPetStore);
	}
	
	/**
	 * @param petStore
	 * @param petStoreData
	 */
	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setAddress(petStoreData.getAddress());
		petStore.setCity(petStoreData.getCity());
		petStore.setState(petStoreData.getState());
		petStore.setZip(petStoreData.getZip());
		petStore.setPetStoreId(petStoreData.getPetStoreId());
		petStore.setPhone(petStoreData.getPhone());
	}

	/**
	 * @param petStoreId
	 * @return
	 */
	private PetStore findOrCreatePetStore(Long petStoreId) {
		if (petStoreId == null) {
			return new PetStore();
		} 
		return findPetStoreById(petStoreId);
	}

	/**
	 * @param petStoreId
	 * @return
	 */
	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId).orElseThrow(() -> 
		new NoSuchElementException("Pet store with ID " + petStoreId + " not found."));
	}

	/**
	 * @param employeeData
	 * @param petStoreId
	 * @return
	 */
	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional(readOnly = false)
	public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee employeeData) {
		System.out.println("Starting saveEmployee...");
		
		PetStore petStore = findPetStoreById(petStoreId);
		System.out.println("Pet store found: " + petStore);
		
		Long employeeId = employeeData.getId();
		System.out.println("Employee ID received: " + employeeId);
		
		Employee employee = findOrCreateEmployee(employeeId, petStoreId);
		System.out.println("Employee found/created: " + employee);
		
		copyEmployeeFields(employee, employeeData);
		System.out.println("Employee fields copied.");
		
		employee.setPetStore(petStore);
		System.out.println("Pet store set for employee.");
		
		if (petStore.getEmployees() == null) {
	        petStore.setEmployees(new HashSet<>());
	    }
		
		petStore.getEmployees().add(employee);
		System.out.println("Employee added to pet store.");
		
		Employee savedEmployee = employeeDao.save(employee);
		System.out.println("Employee saved: " + savedEmployee);
		
		return new PetStoreEmployee(savedEmployee);
	}
	
	private Employee findEmployeeById(Long petStoreId, Long employeeId) {
		Employee employee =  employeeDao.findById(employeeId).orElseThrow(() ->
		new NoSuchElementException("Employee with ID " + employeeId + " not found."));
		
		if (!employee.getPetStore().getPetStoreId().equals(petStoreId)) {
			throw new IllegalArgumentException("Employee with ID " + employeeId + " does not belong to Pet Store with ID " + petStoreId);
		}
		
		return employee;
	}
	
	private Employee findOrCreateEmployee(Long employeeId, Long petStoreId) {
		if (employeeId == null) {
			return new Employee();
		}
		else {
			return findEmployeeById(petStoreId, employeeId);
		}
	}
	
	private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreData) {
		employee.setFirstName(petStoreData.getFirstName());
		employee.setLastName(petStoreData.getLastName());
		employee.setPhone(petStoreData.getPhone());
		employee.setJobTitle(petStoreData.getJobTitle());
	}

	/**
	 * @param customerData
	 * @return
	 */
	
	@Autowired
	private CustomerDao customerDao;
	
	@Transactional(readOnly = false)
	public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer customerData) {
		System.out.println("Starting saveCustomer...");
		
		PetStore petStore = findPetStoreById(petStoreId);
		System.out.println("Pet store found: " + petStore);
		
		Long customerId = customerData.getId();
		System.out.println("Customer ID received: " + customerId);
		
		Customer customer = findOrCreateCustomer(customerId, petStoreId);
		System.out.println("Customer found/created: " + customer);
		
		copyCustomerFields(customer, customerData);
		System.out.println("Customer fields copied.");
		
		if (customer.getPetStores() == null) {
			customer.setPetStores(new HashSet<>());
		}
		
		customer.getPetStores().add(petStore);
		System.out.println("Pet Store added to customer.");
		
		if (petStore.getCustomers() == null) {
	        petStore.setCustomers(new HashSet<>());
	    }
		
		petStore.getCustomers().add(customer);
		System.out.println("Customer added to pet store.");
		
		Customer savedCustomer = customerDao.save(customer);
		System.out.println("Customer saved: " + savedCustomer);
		
		return new PetStoreCustomer(savedCustomer);
	}
	
	private Customer findCustomerById(Long petStoreId, Long customerId) {
		Customer customer =  customerDao.findById(customerId).orElseThrow(() ->
		new NoSuchElementException("Customer with ID " + customerId + " not found."));
		
		for (PetStore petStore : customer.getPetStores()) {
			if (petStore.getPetStoreId().equals(petStoreId)) {
				return customer;
			}
		}
		
		throw new IllegalArgumentException("Customer with ID " + customerId + " does not belong to Pet Store with ID " + petStoreId);
		
		// How to do the same using .stream()
		// Check if the customer is linked to the pet store
//	    boolean isLinked = customer.getPetStores().stream()
//	        .anyMatch(petStore -> petStore.getPetStoreId().equals(petStoreId));
//
//	    if (!isLinked) {
//	        throw new IllegalArgumentException("Customer with ID " + customerId + 
//	            " does not belong to Pet Store with ID " + petStoreId);
//	    }
//
//	    return customer;
		
	}
	
	private Customer findOrCreateCustomer(Long customerId, Long petStoreId) {
		if (customerId == null) {
			return new Customer();
		}
		else {
			return findCustomerById(petStoreId, customerId);
		}
	}
	
	private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreData) {
		customer.setFirstName(petStoreData.getFirstName());
		customer.setLastName(petStoreData.getLastName());
		customer.setEmail(petStoreData.getEmail());
	}

	/**
	 * 
	 */
	@Transactional(readOnly = true)
	public List<PetStoreData> retrieveAllPetStores() {
	 List<PetStore> petStores = petStoreDao.findAll();
	 
	 return petStores.stream()
			 .map(this::convertToPetStoreData)
			 .collect(Collectors.toList());
	}
	
	private PetStoreData convertToPetStoreData(PetStore petStore) {
		PetStoreData petStoreData = new PetStoreData(petStore);
		
		petStoreData.setCustomers(null);
		petStoreData.setEmployees(null);
		
		return petStoreData;
	}

	/**
	 * @param petStoreId
	 * @return
	 */
	@Transactional
	public PetStoreData getPetStoreById(Long petStoreId) {
		PetStore petStore = findPetStoreById(petStoreId);
		return convertToPetStoreData(petStore);
	}

	/**
	 * @param petStoreId
	 * @return
	 */
	@Transactional
	public void deletePetStoreById(Long petStoreId) {
		PetStore petStore = findPetStoreById(petStoreId);
		petStoreDao.delete(petStore);
	}

}
