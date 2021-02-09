package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.*;
import nl.jurgen.garage.payload.request.AppointmentRequest;
import nl.jurgen.garage.payload.request.RegisterUserRequest;
import nl.jurgen.garage.payload.response.ResponseFile;
import nl.jurgen.garage.payload.response.ResponseMessage;
import nl.jurgen.garage.service.CarinspectionService;
import nl.jurgen.garage.service.ClientService;
import nl.jurgen.garage.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    CarinspectionService carinspectionService;

    @Autowired
    FileStorageService storageService;

    //List all clients
    @GetMapping(value = "/list")
    public ResponseEntity<Object> getClients(){
        List<Client> clients =  clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    //Get client by ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getClient(@PathVariable("id") long id){
        Client client =  clientService.getClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //Post new client and return new ID
    @PostMapping(value = "/")
    public ResponseEntity<Object> saveClient(@RequestBody RegisterUserRequest registerUserRequest){
        long newId = clientService.saveClient(registerUserRequest);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    //delete client by ID
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable("id") long id){
        clientService.deleteClient(id);
        return new ResponseEntity<>("Client with ID " + id + " deleted",HttpStatus.NO_CONTENT);
    }

    //Change Client by ID
    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable("id") long id, @RequestBody Client client){
        clientService.updateClient(id, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Find Client by lastname
    @GetMapping(value = "/lastname/{lastname}")
    public ResponseEntity<Object> getClientLastName(@PathVariable("lastname") String lastName){
        Client client =  clientService.getClientByLastName(lastName);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //Get car from client
    @GetMapping(value = "/car/{id}")
    public ResponseEntity<Object> getClientCarById(@PathVariable("id") long id){
        Client client = clientService.getClientById(id);
        Car car = client.getCars();
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    //Add a new car to a client
    @PostMapping(value = "/car/{id}")
    public ResponseEntity<Object> addCarAndLinkById(@PathVariable long id,
                                                    @RequestBody Car car){

        Client client = clientService.getClientById(id);
        long carId = clientService.saveCarById(id, car);
        return new ResponseEntity<>("CarId: " + carId + " Toegevoegd aan client: " +
                client.getFirstName() + " " + client.getLastName(), HttpStatus.CREATED);
    }

    //Create an appointment for a carinspection using a date and the client id
    @PostMapping(value = "/appointment/{id}")
    public ResponseEntity<Object> createAppointment(@PathVariable long id,
                                                    @RequestBody AppointmentRequest appointmentRequest){

        long clientId = carinspectionService.saveAppointment(id, appointmentRequest);
        return new ResponseEntity<>(clientId, HttpStatus.CREATED);
    }

    @GetMapping(value = "clientstocall/list")
    public ResponseEntity<Object> getClientListWithFinishedCarinspection(){
        List<Client> clients = carinspectionService.getClientsWithCarinpectionStatusRepairedAndDeclined();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // upload clients documents using client_id
    @PostMapping("/upload/clientid/{id}")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable long id, @RequestParam("file") MultipartFile file) {

        try {
            storageService.store(file, id);

            String message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            String message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getFileById(@PathVariable String id) {
        FileDB fileDB = storageService.getFileById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
