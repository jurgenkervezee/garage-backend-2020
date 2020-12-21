package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
