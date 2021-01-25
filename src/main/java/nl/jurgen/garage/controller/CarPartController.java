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
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getcarPartById(@PathVariable("id") long id){
        CarPart carPart =  carPartService.getCarPartById(id);
        return new ResponseEntity<>(carPart, HttpStatus.OK);
    }

    //update CarpartStockAmount
    @PutMapping(value = "/updatestock/{id}/{amount}")
    public ResponseEntity<Object> updateStockAmountById(@PathVariable("id") int id, @PathVariable("amount") int amount){
        carPartService.updateStockAmount(id, amount);
        return new ResponseEntity<>("Voorraad mutatie succesvol doorgevoerd" ,HttpStatus.OK);
    }

    //update carpart by id and body
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateCarpartById(@PathVariable("id") int id, @RequestBody CarPart carPart){
        CarPart savedCarPart = carPartService.updateCarpartById(id, carPart);
        return new ResponseEntity<>(savedCarPart ,HttpStatus.OK);
    }

    //add a new carpart
    @PostMapping(value = "/")
    public ResponseEntity<Object> saveCarpart(@RequestBody CarPart carPart){
        Long newId = carPartService.saveCarpart(carPart);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    //delete Carpart
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteCarpart(@PathVariable("id") long id){
        carPartService.deleteCarpart(id);
        return new ResponseEntity<>("Carpart with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }

}
