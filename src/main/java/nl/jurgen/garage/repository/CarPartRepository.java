package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.CarPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPartRepository extends JpaRepository<CarPart, Long> {
}
