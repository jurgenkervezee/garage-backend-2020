package nl.jurgen.garage.service;

import nl.jurgen.garage.model.Client;
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
        //arrange
        Mockito
                .when(clientRepository.existsById((long) 1))
                .thenReturn(true);

        Mockito
                .when(clientRepository.findById((long) 1))
                .thenReturn(Optional.of(client));

        Assertions.assertEquals(clientService.getClientById(1).getFirstName(), client.getFirstName());
    }
}