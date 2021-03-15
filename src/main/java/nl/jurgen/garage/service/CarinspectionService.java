package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.DuplicateRecordInDatabase;
import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.exception.StatusErrorException;
import nl.jurgen.garage.model.*;
import nl.jurgen.garage.payload.request.AppointmentRequest;
import nl.jurgen.garage.payload.request.OrderlineCustomRequest;
import nl.jurgen.garage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    RepairActivityRepository repairActivityRepository;

    @Autowired
    OrderlineRepository orderlineRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    CalculationService calculationService;

    @Autowired
    StatusService statusService;

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

    public void addCarpartToOrderline(long carinspectionId, long carpartId, int amount ) {

        if(carinspectionRepository.existsById(carinspectionId) && carPartRepository.existsById(carpartId) && amount > 0){
            Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);

            if(carinspection.getStatus().getName()==EStatus.OPEN){
                Carpart carpart = carPartRepository.findById(carpartId).orElse(null);

                Orderline orderline = new Orderline(carpart, amount);
                saveOrderlineToDbase(carinspection, orderline);
            }else{
                throw new StatusErrorException();
            }
        }else{
            throw new RecordNotFoundException();
        }
    }

    public void addRepairActivityToCarinspection(long carinspectionId, long repairactivityId, int amount) {

        if(carinspectionRepository.existsById(carinspectionId) && repairActivityRepository.existsById(repairactivityId) && amount > 0){
            Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);

            if(carinspection.getStatus().getName()==EStatus.OPEN){
                RepairActivity repairActivity = repairActivityRepository.findById(repairactivityId).orElse(null);

                Orderline orderline = new Orderline(repairActivity, amount);
                saveOrderlineToDbase(carinspection, orderline);
            }else {
                throw new StatusErrorException();
            }
        }else {
            throw new RecordNotFoundException();
        }
    }

    public void addCustomActivityToOrderline(long carinspectionId, OrderlineCustomRequest orderlineCustomRequest) {

        if(carinspectionRepository.existsById(carinspectionId)){
            Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);

            if(carinspection.getStatus().getName()==EStatus.OPEN){
                Orderline orderline = new Orderline(orderlineCustomRequest);
                saveOrderlineToDbase(carinspection, orderline);
            }else {
                throw new StatusErrorException();
            }
        }else{
            throw new RecordNotFoundException();
        }
    }

    public void saveOrderlineToDbase(Carinspection carinspection, Orderline orderline){

        carinspection.addOrderline(orderline);
        orderline.setCarinspection(carinspection);
        orderlineRepository.save(orderline);
    }

    public Client getClientByCarinspectionId(long carinspectionId){

        if(carinspectionRepository.existsById(carinspectionId)){

            Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);
            return clientRepository.findById(carinspection.getClient().getId()).orElse(null);
        }else {
            throw new RecordNotFoundException();
        }
    }

    public List<Client> getClientsByCarinspectionStatus(){

        List<Client> clientsToCall = new ArrayList<>();
        List<Carinspection> carinspectionListRepaired = carinspectionRepository.findAllByStatus_Name(EStatus.REPAIRED);
        List<Carinspection> carinspectionListDeclined = carinspectionRepository.findAllByStatus_Name(EStatus.REPAIR_DECLINED);

        if(carinspectionListRepaired.size()>0){
            for (Carinspection i : carinspectionListRepaired){
                clientsToCall.add(i.getClient());
            }
        }

        if(carinspectionListDeclined.size()>0){
            for (Carinspection i : carinspectionListDeclined){
                clientsToCall.add(i.getClient());
            }
        }
        return clientsToCall;
    }

    public void deleteAppointment(long id) {
        if(carinspectionRepository.existsById(id)) {
            carinspectionRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException();
        }
    }

    public Double finalizeCarinspectionAndRetrieveTotalPrice(long carinspectionId) {

        if(carinspectionRepository.existsById(carinspectionId)){
            List<Orderline> orderlineList = orderlineRepository.getOrderlinesByCarinspectionId(carinspectionId);

            double priceExVat = calculationService.getPriceExVatForRepairByCarinspection(orderlineList);
            double priceVat = calculationService.calculateVat(priceExVat);

            return Math.round((priceExVat+priceVat)*100)/100.0d;

        } else {
            throw new RecordNotFoundException();
        }
    }

    public Double declineRepair(long carinspectionId) {

        if (carinspectionRepository.existsById(carinspectionId)) {
            Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);

            if (carinspection.getStatus().getName() == EStatus.INSPECTED) {

                Set<Orderline> orderlineSet = carinspection.getOrderlines();
                orderlineSet.removeAll(orderlineSet);

                carinspection.setOrderlines(orderlineSet);
                addCarInspectionCost(carinspectionId);

                carinspectionRepository.save(carinspection);

                statusService.changeStatus(carinspectionId, EStatus.REPAIR_DECLINED);

                return finalizeCarinspectionAndRetrieveTotalPrice(carinspectionId);

            } else {
                throw new StatusErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void addCarInspectionCost(long carinspectionId){

        if(carinspectionRepository.existsById(carinspectionId)){
            Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);

            EStatus status = carinspection.getStatus().getName();

            if(status==EStatus.OPEN || status == EStatus.INSPECTED){
                Orderline orderline = new Orderline("Carinspection", 1, 45.00);
                orderline.setCarinspection(carinspection);
                carinspection.addOrderline(orderline);

            }else {
                throw new StatusErrorException();
            }
        }else {
            throw new RecordNotFoundException();
        }
    }

}
