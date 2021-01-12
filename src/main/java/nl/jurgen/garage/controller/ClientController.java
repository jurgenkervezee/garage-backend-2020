package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.Car;
import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.payload.request.RegisterUserRequest;
import nl.jurgen.garage.service.CarService;
import nl.jurgen.garage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    //List all clients
    @GetMapping(value = "/client")
    public ResponseEntity<Object> getClients(){
        List<Client> clients =  clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    //Get client by ID
    @GetMapping(value = "/client/{id}")
    public ResponseEntity<Object> getClient(@PathVariable("id") long id){
        Client client =  clientService.getClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //delete client by ID
    @DeleteMapping(value = "/client/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable("id") long id){
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Post new client and return new ID
    @PostMapping(value = "/client")
    public ResponseEntity<Object> saveClient(@RequestBody RegisterUserRequest registerUserRequest){
        long newId = clientService.saveClient(registerUserRequest);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    //Change Client by ID
    @PutMapping(value = "/client/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable("id") long id, @RequestBody Client client){
        clientService.updateClient(id, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Find Client by lastname
    @GetMapping(value = "/client/lastname/{lastname}")
    public ResponseEntity<Object> getClientLastName(@PathVariable("lastname") String lastName){
        Client client =  clientService.getClientByLastName(lastName);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //Get car from client
    @GetMapping(value = "/client/car/{id}")
    public ResponseEntity<Object> getClientCarById(@PathVariable("id") long id){
        Car car = clientService.getCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    //Add a new car to a client
    @PostMapping(value = "/client/car/{id}")
    public ResponseEntity<Object> addCarAndLinkById(@PathVariable long id, @RequestBody Car car){

        Client client = clientService.getClientById(id);
        long carId = clientService.saveCarById(id, car);

        return new ResponseEntity<>(carId + " Toegevoegd aan client: " +
                client.getFirstName() + " " + client.getLastName(), HttpStatus.OK);
    }
}
