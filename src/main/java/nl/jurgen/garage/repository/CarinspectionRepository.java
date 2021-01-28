package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.Carinspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarinspectionRepository extends JpaRepository <Carinspection, Long >{
}
