package nl.jurgen.garage.service;

import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.payload.request.RegisterUserRequest;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();
    Client getClientById(long id);
    void deleteClient(long id);
    long saveClient(RegisterUserRequest registerUserRequest);
    void updateClient(long id, Client client);
    Client getClientByLastName(String lastName);

}
