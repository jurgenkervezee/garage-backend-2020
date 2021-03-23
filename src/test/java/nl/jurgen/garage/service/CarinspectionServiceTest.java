package nl.jurgen.garage.service;

import nl.jurgen.garage.model.*;
import nl.jurgen.garage.repository.CarinspectionRepository;
import nl.jurgen.garage.repository.StatusRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@ExtendWith(SpringExtension.class)
class CarinspectionServiceTest {
@InjectMocks
CarinspectionService carinspectionService;
@InjectMocks
StatusService statusService;
@Mock
CarinspectionRepository carinspectionRepository;
@Mock
StatusRepository statusRepository;
@Mock
Carinspection carinspection;
@Mock
Client client;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        //create carinspectionObject
        Date date = new Date();
        Status status = new Status();
        status.setName(EStatus.REPAIR_DECLINED);
        carinspection = new Carinspection(date, status);
        carinspection.setId(1);
        carinspection.setOrderlines(new HashSet<>());
        //create ClientObject
        client = new Client();
        client.setId(1);
        client.setFirstName("Voornaam");
        client.setLastName("Achternaam");
        client.setTelephoneNumber("telephoneNumber");
   }

    @Test
    void getCarinspectionById(){
        //Given
        carinspection.setClient(client);
        Mockito
                .when(carinspectionRepository.findById(carinspection.getId()))
                .thenReturn(Optional.of(carinspection));
        //When
        Optional<Carinspection> carinspectionFound = carinspectionRepository.findById((long) 1);
        //Then
        Assertions.assertEquals(carinspectionFound.get().getId(), 1 );
    }

    @Test
    void getClientsByCarinspectionStatusRepairedDeclined() {
        //Given
        List<Carinspection> carinspectionList = new ArrayList<>();
        carinspection.setClient(client);
        carinspectionList.add(carinspection);
        Mockito
                .when(carinspectionRepository.findAllByStatus_Name(EStatus.REPAIR_DECLINED))
                .thenReturn(carinspectionList);

        //When
        List<Client> clientList;
        clientList = carinspectionService.getClientsByCarinspectionStatus();

        //Then
        Assertions.assertEquals(clientList.get(0).getLastName(), client.getLastName());
    }
    @Test

    void getClientsByCarinspectionStatusRepaired() {
        //Given
        Status status = new Status(EStatus.REPAIRED);
        carinspection.setStatus(status);
        List<Carinspection> carinspectionList = new ArrayList<>();
        carinspection.setClient(client);
        carinspectionList.add(carinspection);
        Mockito
                .when(carinspectionRepository.findAllByStatus_Name(EStatus.REPAIRED))
                .thenReturn(carinspectionList);
        //When
        List<Client> clientList;
        clientList = carinspectionService.getClientsByCarinspectionStatus();

        //Then
        Assertions.assertEquals(clientList.get(0).getLastName(), client.getLastName());
    }

    @Test
    void finalizeCarinspectionAndRetrieveTotalPrice() {
        //Nog maken
    }


    @Test
    void declineRepair() {
        //Nog maken
    }

    @Test
    void saveAppointment() {
        //Nog maken
    }
}