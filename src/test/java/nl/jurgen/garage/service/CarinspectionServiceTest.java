package nl.jurgen.garage.service;

import nl.jurgen.garage.model.*;
import nl.jurgen.garage.repository.CarinspectionRepository;
import nl.jurgen.garage.repository.StatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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
//@Mock
//Orderline orderline;
//@Mock
//Status status;

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
        List<Client> clientList = new ArrayList<>();
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
    }

    @Test
    void declineRepair() {
        //Given
        Status status = new Status(EStatus.INSPECTED);
        carinspection.setStatus(status);
        Orderline orderline = new Orderline("Description", 1, 100.00);

        carinspection.addOrderline(orderline);

        Mockito
                .when(carinspectionRepository.existsById((long) 1))
                .thenReturn(true);
        Mockito
                .when(statusRepository.findByName(EStatus.INSPECTED))
                .thenReturn(status);
        Mockito
                .when(carinspectionRepository.findById(carinspection.getId()))
                .thenReturn(Optional.of(carinspection));

        //When
        double result = carinspectionService.declineRepair(1);
        //Then
        Assertions.assertEquals(45.00, result);
    }


//
//        if (carinspection.getStatus().getName() == EStatus.INSPECTED) {
//
//            Set<Orderline> orderlineSet = carinspection.getOrderlines();
//            orderlineSet.removeAll(orderlineSet);
//
//            carinspection.setOrderlines(orderlineSet);
//            addCarInspectionCost(carinspectionId);
//
//            statusService.changeStatus(carinspectionId, EStatus.REPAIR_DECLINED);
//            carinspectionRepository.save(carinspection);
//
//            return finalizeCarinspectionAndRetrieveTotalPrice(carinspectionId);
}