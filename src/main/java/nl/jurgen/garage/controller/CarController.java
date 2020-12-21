package nl.jurgen.garage.controller;


import nl.jurgen.garage.model.Car;
import nl.jurgen.garage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping(value = "/cars")
    public ResponseEntity<Object> getAllCars(){
        List<Car> cars =  carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
