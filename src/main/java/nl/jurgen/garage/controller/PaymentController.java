package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.EStatus;
import nl.jurgen.garage.model.Orderline;
import nl.jurgen.garage.payload.response.ResponsePayment;
import nl.jurgen.garage.service.PaymentService;
import nl.jurgen.garage.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    StatusService statusService;


    @GetMapping(value = "/carinspectionid/{id}")
    public ResponseEntity<Object> getOrderlinesForCarinspection(@PathVariable long id){

        List<Orderline> orderlineList =  paymentService.getOrderlinesCarinspection(id);

        return new ResponseEntity<>(orderlineList, HttpStatus.OK);
    }

    //List all orderlines for a carinspection and get the total price including VAT also change status to PAID_CLOSED
    @GetMapping(value = "/paid/carinspectionid/{id}")
    public ResponseEntity<Object> getPaymentDetailsForCarinspection(@PathVariable long id){

        ResponsePayment responsePayment = paymentService.getPaymentDetails(id);
        statusService.changeStatus(id, EStatus.PAID_CLOSED);

        return new ResponseEntity<>(responsePayment, HttpStatus.OK);
    }
}
