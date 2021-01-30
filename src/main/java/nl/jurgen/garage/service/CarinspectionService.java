package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.DuplicateRecordInDatabase;
import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.model.Carpart;
import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.model.Orderline;
import nl.jurgen.garage.repository.CarinspectionRepository;
import nl.jurgen.garage.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

             if(!carinspectionRepository.existsByClientIdAndAndDate(clientId, carinspection.getDate())){
                 Set<Carinspection> carinspectionSet = client.getCarinspections();
                 carinspectionSet.add(carinspection);
                 client.setCarinspections(carinspectionSet);
                 carinspection.setClient(client);
                 carinspectionRepository.save(carinspection);
                 return clientId;
             } else {
                 throw new DuplicateRecordInDatabase();
                }
        }else {
            throw new RecordNotFoundException();
        }
    }

    public void addCarpartToOrderline(long id, Carpart carpart, int amount) {

        Carinspection carinspection = carinspectionRepository.findById(id).orElse(null);
        Set<Orderline> orderlines = null;

        if(!carinspection.getOrderlines().isEmpty()) {
            orderlines = carinspection.getOrderlines();
        }

        orderlines.add(new Orderline(amount, carpart));
        carinspection.setOrderlines(orderlines);
        carinspectionRepository.save(carinspection);

    }
}