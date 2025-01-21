/**
 * 
 */
package pet.store.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 */
@Entity // marks this class as a JPA entity, meaning it will be mapped to a database table
@Data
public class Customer {

	@Id // marks field as the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // specifies how primary key value is generated / lets the database handle generation of unique values (auto-increment)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	@ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST) // defines relationship to PetStore / mappedBy says that this side is not the Owning side, which is defined by the PetStore entity instead / when a customer is saved, any associated PetStore entities are also persisted
	@EqualsAndHashCode.Exclude // prevents recursion when comparing objects with circular relationships
	@ToString.Exclude // Exclude this field from the toString method to avoid infinite loops caused by bidirectional relationships
	private Set<PetStore> petStores; // customer can be associated with multiple pet stores, this represents that collection
}
