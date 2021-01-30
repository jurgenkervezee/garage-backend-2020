package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.repository.CarinspectionRepository;
import nl.jurgen.garage.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CarinspectionService {

    @Autowired
    CarinspectionRepository carinspectionRepository;

    @Autowired
    ClientRepository clientRepository;

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

    public long saveAppointment(long clientId, Carinspection carinspection) {

        if(clientRepository.existsById(clientId)){

            Client client = clientRepository.findById(clientId).orElse(null);

            Set<Carinspection> carinspectionSet = client.getCarinspections();
            carinspectionSet.add(carinspection);
            client.setCarinspections(carinspectionSet);

            carinspection.setClient(client);
            carinspectionRepository.save(carinspection);
            return clientId;
        }else {
            throw new RecordNotFoundException();
        }
    }
}
