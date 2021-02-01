package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderlineRepository extends JpaRepository<Orderline, Long> {

    boolean existsByCarinspectionId(long id);
    List<Orderline>  getOrderlinesByCarinspectionId(long id);
}
