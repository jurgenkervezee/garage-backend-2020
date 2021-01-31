package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderlineRepository extends JpaRepository<Orderline, Long> {
}
