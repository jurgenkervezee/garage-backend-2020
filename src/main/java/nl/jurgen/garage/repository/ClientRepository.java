package nl.jurgen.garage.repository;

import nl.jurgen.garage.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByLastName(String lastname);
    Client findByLastNameIgnoreCase(String lastname);
    //Handmatige query

}
