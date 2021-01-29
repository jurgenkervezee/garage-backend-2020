package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.repository.CarinspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarinspectionService {

    @Autowired
    CarinspectionRepository carinspectionRepository;

    public List<Carinspection> getAllInspections() {

        List<Carinspection> carinspectionsList= carinspectionRepository.findAll();
        return carinspectionsList;
    }

    public Carinspection getCarinspectionById(long id) {
        if(carinspectionRepository.existsById(id)){

            Carinspection carinspection = carinspectionRepository.findById(id).orElse(null);
            return carinspection;
        }else {
            throw new RecordNotFoundException();
        }
    }

    public long saveAppointment(Carinspection carinspection) {
        //hier adhv client id client ophalen
        //setCarinspection op client
        //sla client op
        Carinspection newCarinspection = carinspectionRepository.save(carinspection);

        return newCarinspection.getId();
    }
}
