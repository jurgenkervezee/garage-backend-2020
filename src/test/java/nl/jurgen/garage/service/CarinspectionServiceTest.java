package nl.jurgen.garage.service;

import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.model.EStatus;
import nl.jurgen.garage.model.Status;
import nl.jurgen.garage.repository.CarinspectionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
class CarinspectionServiceTest {
@InjectMocks
CarinspectionService carinspectionService;
@Mock
CarinspectionRepository carinspectionRepository;
@Mock
Carinspection carinspection;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        Date date = new Date();
        Status status = new Status();
        status.setName(EStatus.OPEN);
        carinspection = new Carinspection(date, status);
        carinspection.setId(1);
   }

    @Test
    void getCarinspectionById(){
        //Given
        Mockito
                .when(carinspectionRepository.findById(carinspection.getId()))
                .thenReturn(Optional.of(carinspection));
        //When
        Optional<Carinspection> carinspectionFound = carinspectionRepository.findById((long) 1);
        //Then
        Assertions.assertEquals(carinspectionFound.get().getId(), 1 );

    }
    @Test
    void getClientsByCarinspectionStatus() {
    }

    @Test
    void finalizeCarinspectionAndRetrieveTotalPrice() {
    }

    @Test
    void declineRepair() {
    }
}