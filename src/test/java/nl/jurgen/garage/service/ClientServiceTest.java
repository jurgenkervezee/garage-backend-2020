package nl.jurgen.garage.service;

import nl.jurgen.garage.model.Address;
import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.model.ClientBuilder;
import nl.jurgen.garage.payload.request.RegisterUserRequest;
import nl.jurgen.garage.repository.AddressRepository;
import nl.jurgen.garage.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
class ClientServiceTest {
    @InjectMocks
    ClientService clientService;
    @Mock
    ClientRepository clientRepository;
    @Mock
    AddressRepository addressRepository;
    @Mock
    Client client;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        client = new Client();
        client.setId(1);
        client.setFirstName("Voornaam");
        client.setLastName("Achternaam");
        client.setTelephoneNumber("telephoneNumber");

    }
    @Test
    void getClientById() {
        //Given
        Mockito
                .when(clientRepository.existsById((long) 1))
                .thenReturn(true);
        //Then
        Mockito
                .when(clientRepository.findById((long) 1))
                .thenReturn(Optional.of(client));
        //When
        Assertions.assertEquals(clientService.getClientById(1).getFirstName(), client.getFirstName());
    }

    @Test
    void saveClient() {
        //Given
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstName("Voornaam");
        registerUserRequest.setLastName("Achternaam");
        registerUserRequest.setTelephoneNumber("TelephoneNumber");
        registerUserRequest.setStreetName("Straatnaam");
        registerUserRequest.setPostalCode("1000 BA");
        registerUserRequest.setHouseNumber("1");
        registerUserRequest.setHouseNumberAddition("A");
        registerUserRequest.setHomeTown("Bussum");
        Client savedClient = new Client();
        Address address = new Address();

        long id = 1;

        //When
        Mockito
                .when(addressRepository.save(address))
                .thenReturn(address);
        Mockito
                .when(clientRepository.save(savedClient))
                .thenReturn(savedClient);



        long idResult = clientService.saveClient(registerUserRequest);
    }


    @Test
    void getClientByLastName() {
        //Given
        Mockito
                .when(clientRepository.findByLastNameIgnoreCase("Achternaam"))
                .thenReturn(client);
        //When
        Client client = clientService.getClientByLastName("Achternaam");
        //Then
        Assertions.assertEquals("Achternaam", client.getLastName());
    }
}