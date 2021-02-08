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

import java.util.List;
import java.util.Set;

@Service
public class CarinspectionService {

    @Autowired
    private CarinspectionRepository carinspectionRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarPartRepository carPartRepository;

    @Autowired
    private RepairActivityRepository repairActivityRepository;

    @Autowired
    private OrderlineRepository orderlineRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private StatusService statusService;

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
            Carpart carpart = carPartRepository.findById(carpartId).orElse(null);

            Orderline orderline = new Orderline(carpart, amount);
            saveOrderlineToDbase(carinspection, orderline);
        }else{
            throw new RecordNotFoundException();
        }
    }

    public void addRepairActivityToCarinspection(long carinspectionId, long repairactivityId, int amount) {

        if(carinspectionRepository.existsById(carinspectionId) && repairActivityRepository.existsById(repairactivityId) && amount > 0){

            Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);
            RepairActivity repairActivity = repairActivityRepository.findById(repairactivityId).orElse(null);
            Orderline orderline = new Orderline(repairActivity, amount);
            saveOrderlineToDbase(carinspection, orderline);

        }else {
            throw new RecordNotFoundException();
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

    public Double finalizeCarinspectionAndRetrieveTotalPrice(long carinspectionId) {

        if(carinspectionRepository.existsById(carinspectionId)){
         //   addCarInspectionCost(carinspectionId);
            List<Orderline> orderlineList = orderlineRepository.getOrderlinesByCarinspectionId(carinspectionId);

            double priceExVat = calculationService.getPriceExVatForRepairByCarinspection(orderlineList);
            double priceVat = calculationService.calculateVat(priceExVat);
            statusService.changeStatus(carinspectionId, EStatus.INSPECTED);

            return Math.round((priceExVat+priceVat)*100)/100.0d;

        }else {
            throw new RecordNotFoundException();
        }
    }

    public Double declineRepair(long carinspectionId) {

        if (carinspectionRepository.existsById(carinspectionId)) {
            Carinspection carinspection = carinspectionRepository.findById(carinspectionId).orElse(null);

            if (carinspection.getStatus().getName() == EStatus.OPEN) {

                Set<Orderline> orderlineSet = carinspection.getOrderlines();
                orderlineSet.removeAll(orderlineSet);

                carinspection.setOrderlines(orderlineSet);
                addCarInspectionCost(carinspectionId);

                statusService.changeStatus(carinspectionId, EStatus.REPAIR_DECLINED);
                carinspectionRepository.save(carinspection);

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
            Orderline orderline = new Orderline("Carinspection", 1, 45.00);
            orderline.setCarinspection(carinspection);
            carinspection.addOrderline(orderline);

        }else {
            throw new RecordNotFoundException();
        }
    }
}
