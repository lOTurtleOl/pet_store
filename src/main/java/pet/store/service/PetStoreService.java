/**
 * 
 */
package pet.store.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
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
	 * @param customerData
	 * @return
	 */
	public PetStoreCustomer saveCustomer(PetStoreCustomer customerData) {
		// TODO Auto-generated method stub
		return null;
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
	 * @param customerData
	 * @return
	 */

}
