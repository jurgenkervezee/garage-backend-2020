package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.DatabaseErrorException;
import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.Address;
import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.model.ClientBuilder;
import nl.jurgen.garage.payload.request.RegisterUserRequest;
import nl.jurgen.garage.repository.AddressRepository;
import nl.jurgen.garage.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(long id) {
        if(clientRepository.existsById(id)){

            return clientRepository.findById(id).orElse(null);
        }else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteClient(long id) {
        if(clientRepository.existsById(id)){
            clientRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException();
        }
    }

    @Override
    public long saveClient(RegisterUserRequest registerUserRequest) {

        Client client = new ClientBuilder(registerUserRequest).buildClient();
        Address address = new ClientBuilder(registerUserRequest).buildAddress();

        Address savedAddress = addressRepository.save(address);
        client.setAddress(savedAddress);

        return clientRepository.save(client).getId();
    }



    @Override
    public void updateClient(long id, Client client) {
        if(clientRepository.existsById(id)){
            try {
                Client existingClient = clientRepository.findById(id).orElse(null);
                existingClient.setFirstName(client.getFirstName());
                existingClient.setLastName(client.getLastName());
                existingClient.setClientNumber(client.getClientNumber());
                clientRepository.save(existingClient);
            } catch (Exception e){
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Client getClientByLastName(String lastName) {

            return clientRepository.findByLastNameIgnoreCase(lastName);
    }
}
