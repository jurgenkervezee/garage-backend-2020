package nl.jurgen.garage.repository;


import nl.jurgen.garage.model.EStatus;
import nl.jurgen.garage.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findByName(EStatus name);
}
