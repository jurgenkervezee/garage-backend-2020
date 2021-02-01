package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.Orderline;
import nl.jurgen.garage.repository.OrderlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    OrderlineRepository orderlineRepository;


    public List<Orderline> getOrderlinesCarinspection(long carinspectionId) {

        if(orderlineRepository.existsByCarinspectionId(carinspectionId)){

            return orderlineRepository.getOrderlinesByCarinspectionId(carinspectionId);
        }else {
            throw new RecordNotFoundException();
        }

    }
}
