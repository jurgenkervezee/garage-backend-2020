package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
