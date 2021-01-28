package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.Carpart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPartRepository extends JpaRepository<Carpart, Long> {
}
