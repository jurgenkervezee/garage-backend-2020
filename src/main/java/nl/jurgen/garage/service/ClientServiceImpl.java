package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.DatabaseErrorException;
import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.Address;
import nl.jurgen.garage.model.Car;
import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.model.ClientBuilder;
import nl.jurgen.garage.payload.request.RegisterUserRequest;
import nl.jurgen.garage.repository.AddressRepository;
import nl.jurgen.garage.repository.CarRepository;
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
    @Autowired
    CarRepository carRepository;

    @Override
    public List<Client> getAllClients() {
        List<Client> clients= clientRepository.findAll();
        for(Client a: clients){
            a.setAddress(null);
            a.setCars(null);
        }
        return clients;
    }

    @Override
    public Client getClientById(long id) {
        if(clientRepository.existsById(id)){

            Client client = clientRepository.findById(id).orElse(null);
            client.setAddress(null);
            client.setCars(null);

            return client;
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
        Address address = new ClientBuilder(registerUserRequest)
                .withHousenumberAddition(registerUserRequest)
                .buildAddress();

        Address savedAddress = addressRepository.save(address);
        client.setAddress(savedAddress);
        address.setClient(client);

        return clientRepository.save(client).getId();
    }



    @Override
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

    @Override
    public Client getClientByLastName(String lastName) {
            Client client = clientRepository.findByLastNameIgnoreCase(lastName);
            client.setAddress(null);
            client.setCars(null);
            return client;
    }

    @Override
    public Car getCarById(long id) {
        if(carRepository.existsById(id)){
                    }
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public long saveCarById(long clientId, Car car) {
        if((clientRepository.existsById(clientId))){

            Client client = clientRepository.findById(clientId).orElse(null);
            car.setClient(client);
            long carId = carRepository.save(car).getId();
            return carId;

        }else throw new RecordNotFoundException();
    }
}
