package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.Carinspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarInspectionRepository extends JpaRepository <Carinspection, Long >{
}
