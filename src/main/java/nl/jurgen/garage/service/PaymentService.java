package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.EStatus;
import nl.jurgen.garage.model.Orderline;
import nl.jurgen.garage.payload.response.ResponsePayment;
import nl.jurgen.garage.repository.OrderlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    OrderlineRepository orderlineRepository;

    @Autowired
    CalculationService calculationService;

    public List<Orderline> getOrderlinesCarinspection(long carinspectionId) {

        if(orderlineRepository.existsByCarinspectionId(carinspectionId)){
            return orderlineRepository.getOrderlinesByCarinspectionId(carinspectionId);
        }else {
            throw new RecordNotFoundException();
        }
    }

    public ResponsePayment getPaymentDetails(long carinspectionId){

        if(orderlineRepository.existsByCarinspectionId(carinspectionId)){

            List<Orderline> orderlines = orderlineRepository.getOrderlinesByCarinspectionId(carinspectionId);
            double totalPriceExVat = calculationService.getPriceExVatForRepairByCarinspection(orderlines);
            double totalVatPrice = calculationService.calculateVat(totalPriceExVat);

            return new ResponsePayment(orderlines, totalPriceExVat, totalVatPrice);
        }else{
            throw new RecordNotFoundException();
        }
    }
}
