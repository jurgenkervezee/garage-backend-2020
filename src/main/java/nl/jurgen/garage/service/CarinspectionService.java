package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.DuplicateRecordInDatabase;
import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.model.Carpart;
import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.model.Orderline;
import nl.jurgen.garage.payload.request.OrderlineCustomRequest;
import nl.jurgen.garage.repository.CarPartRepository;
import nl.jurgen.garage.repository.CarinspectionRepository;
import nl.jurgen.garage.repository.ClientRepository;
import nl.jurgen.garage.repository.OrderlineRepository;
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

    @Autowired
    CarPartRepository carPartRepository;

    @Autowired
    OrderlineRepository orderlineRepository;

    public List<Carinspection> getAllInspections() {

        return carinspectionRepository.findAll();
    }

    public Carinspection getCarinspectionById(long id) {
        if(carinspectionRepository.existsById(id)){

            return carinspectionRepository.findById(id).orElse(null);
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

    public void addCarpartToOrderline(long carinspectionId, long carpartId, int carpartAmount ) {

        Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);
        Carpart carpart = carPartRepository.findById(carpartId).orElse(null);

        Orderline orderline = new Orderline(carpart, carpartAmount);

        if (carinspection != null) {
            saveOrderlineToDbase(carinspection, orderline);
        }

    }

    public void addCustomActivityToOrderline(long carinspectionId, OrderlineCustomRequest orderlineCustomRequest) {

        Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);
        Orderline orderline = new Orderline(orderlineCustomRequest);

        if (carinspection != null) {
            saveOrderlineToDbase(carinspection, orderline);
        }
    }

    public void saveOrderlineToDbase(Carinspection carinspection, Orderline orderline){

        carinspection.addOrderline(orderline);
        carinspectionRepository.save(carinspection);
        orderline.setCarinspection(carinspection);
        orderlineRepository.save(orderline);
    }

    public Client getClientByCarinspectionId(long carinspectionId){

         Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);

        return clientRepository.findById(carinspection.getClient().getId()).orElse(null);
    }
}
