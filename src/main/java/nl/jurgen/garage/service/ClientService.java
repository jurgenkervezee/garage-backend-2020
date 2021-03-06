package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.DatabaseErrorException;
import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.*;
import nl.jurgen.garage.payload.request.RegisterUserRequest;
import nl.jurgen.garage.repository.AddressRepository;
import nl.jurgen.garage.repository.CarRepository;
import nl.jurgen.garage.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService{

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CarRepository carRepository;


    public List<Client> getAllClients() {

        return clientRepository.findAll();
    }

    public Client getClientById(long id) {
        if(clientRepository.existsById(id)){

            return clientRepository.findById(id).orElse(null);
        }else {
            throw new RecordNotFoundException();
        }
    }

    public void deleteClient(long id) {
        if(clientRepository.existsById(id)){
            clientRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException();
        }
    }

    public long saveClient(RegisterUserRequest registerUserRequest) {

        Client client = new ClientBuilder(registerUserRequest).buildClient();
        Address address = new ClientBuilder(registerUserRequest)
                .withHousenumberAddition(registerUserRequest)
                .buildAddress();

        Address savedAddress = addressRepository.save(address);
        client.setAddress(savedAddress);
        address.setClient(client);

        return clientRepository.save(client).getId();
    }

    public void updateClient(long id, Client client) {
        if(clientRepository.existsById(id)){
            try {
                Client existingClient = clientRepository.findById(id).orElse(null);
                existingClient.setFirstName(client.getFirstName());
                existingClient.setLastName(client.getLastName());
                existingClient.setTelephoneNumber(client.getTelephoneNumber());
                clientRepository.save(existingClient);
            } catch (Exception e){
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }


    public Client getClientByLastName(String lastName) {
            Client client = clientRepository.findByLastNameIgnoreCase(lastName);
            return client;
    }


    public Car getCarById(long id) {
        if(carRepository.existsById(id)){
            return carRepository.findById(id).orElse(null);
        }else throw new RecordNotFoundException();
    }


    public long saveCarById(long clientId, Car car) {
        if((clientRepository.existsById(clientId))){

            Client client = clientRepository.findById(clientId).orElse(null);
            car.setClient(client);
            long carId = carRepository.save(car).getId();
            return carId;

        }else throw new RecordNotFoundException();
    }
}
