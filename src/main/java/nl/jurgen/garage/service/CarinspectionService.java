package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.DuplicateRecordInDatabase;
import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.*;
import nl.jurgen.garage.payload.request.AppointmentRequest;
import nl.jurgen.garage.payload.request.OrderlineCustomRequest;
import nl.jurgen.garage.repository.*;
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

    @Autowired
    StatusRepository statusRepository;

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

    public long saveAppointment(long clientId, AppointmentRequest appointmentRequest) {

        Status status = statusRepository.findByName(appointmentRequest.getStatus().getName());
        Carinspection carinspection = new Carinspection(appointmentRequest.getDate(), status);

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
        orderline.setCarinspection(carinspection);
        orderlineRepository.save(orderline);
    }

    public Client getClientByCarinspectionId(long carinspectionId){

         Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);
        return clientRepository.findById(carinspection.getClient().getId()).orElse(null);
    }

    public void deleteAppointment(long id) {
        if(carinspectionRepository.existsById(id)) {
            carinspectionRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException();
        }
    }

    public Double getPriceForRepairByCarinspection(long carinspectionid) {

        double totalPrice = 0;
        List<Orderline> orderlineList = orderlineRepository.getOrderlinesByCarinspectionId(carinspectionid);

        for(Orderline orderline: orderlineList){
            double price = orderline.getPrice() * orderline.getAmount();
            totalPrice = totalPrice + price;
        }
        return Math.round(totalPrice*100)/100.0d;
    }

    public Double declineRepair(long carinspectionid) {

        if(carinspectionRepository.existsById(carinspectionid)){

            Carinspection carinspection = carinspectionRepository.findById(carinspectionid).orElse(null);
            Set<Orderline> orderlineSet = carinspection.getOrderlines();
            orderlineSet.removeAll(orderlineSet);

            Orderline orderline = new Orderline("Carinspection", 1, 45.00);
            orderline.setCarinspection(carinspection);
            carinspection.addCarinspectionCosts(orderline);
            carinspection.setOrderlines(orderlineSet);

            Status status = statusRepository.findByName(EStatus.REPAIR_DECLINED);
            carinspection.setStatus(status);
//            orderline.setCarinspection(carinspection);

            carinspectionRepository.save(carinspection);

            return getPriceForRepairByCarinspection(carinspectionid);

        }else {
            throw new RecordNotFoundException();
        }


    }


}
