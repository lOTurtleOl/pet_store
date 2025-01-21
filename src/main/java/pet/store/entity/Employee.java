/**
 * 
 */
package pet.store.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * 
 */
@Entity
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // long maps to BIGINT data type in databases
	
	private String firstName;
	
	private String lastName;
	
	private Long phone;
	
	private String jobTitle;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_store_id") // creates a foreign key column in employee table / specifies the foreign key in the table
	private PetStore petStore;
}
