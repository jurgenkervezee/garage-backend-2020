package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.CarInspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarInspectionRepository extends JpaRepository <CarInspection, Long >{
}
