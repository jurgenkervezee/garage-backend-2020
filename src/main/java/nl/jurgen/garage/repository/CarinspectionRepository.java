package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CarinspectionRepository extends JpaRepository <Carinspection, Long >{

    boolean existsByClientIdAndAndDate(long id, Date date);

}
