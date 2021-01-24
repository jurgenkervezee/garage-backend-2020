package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.CarPart;
import nl.jurgen.garage.service.CarPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carparts")
public class CarPartController {

    @Autowired
    private CarPartService carPartService;

    //get a listing of all the carparts
    @GetMapping(value = "/list")
    public ResponseEntity<Object> getAllCarParts(){
        List<CarPart> carparts = carPartService.getAllCarParts();
        return new ResponseEntity<>(carparts, HttpStatus.OK);
    }

    //get a carpart by id
    @GetMapping(value = "/carparts/{id}")
    public ResponseEntity<Object> getcarPartById(@PathVariable("id") long id){
        CarPart carPart =  carPartService.getCarPartById(id);
        return new ResponseEntity<>(carPart, HttpStatus.OK);
    }

    //update CarpartStockAmount
    @PutMapping(value = "carparts/{id}/{amount}")
    public ResponseEntity<Object> updateStockAmount(@PathVariable("id") int id, @PathVariable("amount") int amount){
        carPartService.updateStockAmount(id, amount);
        return new ResponseEntity<>("Voorraad mutatie succesvol doorgevoerd" ,HttpStatus.OK);
    }
    //add a new carpart

    //delete Carpart

    //update description of a carpart

    //update a price of a product

}
