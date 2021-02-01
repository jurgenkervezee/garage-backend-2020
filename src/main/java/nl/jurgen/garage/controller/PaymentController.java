package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.model.Orderline;
import nl.jurgen.garage.service.PaymentService;
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

    //List all orderlines for a carinspection
    @GetMapping(value = "/carinspection/{id}")
    public ResponseEntity<Object> getOrderlinesForCarinspection(@PathVariable long id){

        List<Orderline> orderlineList =  paymentService.getOrderlinesCarinspection(id);
        return new ResponseEntity<>(orderlineList, HttpStatus.OK);
    }
}
