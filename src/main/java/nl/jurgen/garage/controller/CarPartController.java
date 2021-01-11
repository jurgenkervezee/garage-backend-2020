package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.CarPart;
import nl.jurgen.garage.service.CarPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarPartController {

    @Autowired
    private CarPartService carPartService;

    //get a listing of all the carparts
    @GetMapping(value = "/carparts")
    public ResponseEntity<Object> getAllCarParts(){
        List<CarPart> carparts = carPartService.getAllCarParts();
        return new ResponseEntity<>(carparts, HttpStatus.OK);
    }

    //get a carpart by id
    @GetMapping(value = "/carpart/{id}")
    public ResponseEntity<Object> getcarPartById(@PathVariable("id") long id){
        CarPart carPart =  carPartService.getCarPartById(id);
        return new ResponseEntity<>(carPart, HttpStatus.OK);
    }

    //update CarpartStockAmount
    @PutMapping(value = "carpart/{id}/{amount}")
    public ResponseEntity<Object> updateStockAmount(@PathVariable("id") int id, @PathVariable("amount") int amount){
        carPartService.updateStockAmount(id, amount);
        return new ResponseEntity<>("Voorraad mutatie succesvol doorgevoerd" ,HttpStatus.OK);
    }



}
