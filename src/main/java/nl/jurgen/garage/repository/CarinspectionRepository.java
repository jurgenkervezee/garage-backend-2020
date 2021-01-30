package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface CarinspectionRepository extends JpaRepository <Carinspection, Long >{

    boolean existsByClientId(long id);
    boolean existsByDate(Date date);
    boolean existsByClientIdAndAndDate(long id, Date date);
}
