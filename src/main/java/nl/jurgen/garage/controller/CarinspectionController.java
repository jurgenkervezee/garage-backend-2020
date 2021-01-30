package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.model.Carpart;
import nl.jurgen.garage.service.CarinspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inspections")
public class CarinspectionController {

    @Autowired
    private CarinspectionService carinspectionService;

    // List available inspections
    @GetMapping(value = "/list")
    public ResponseEntity<Object> getAllInspections(){
        List<Carinspection> carinspectionList = carinspectionService.getAllInspections();
        return new ResponseEntity<>(carinspectionList, HttpStatus.OK);
    }

    // Get an appointment by ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getInspectionById(@PathVariable long id){

        Carinspection carinspection = carinspectionService.getCarinspectionById(id);
        return new ResponseEntity<>(carinspection, HttpStatus.OK);
    }

    @PostMapping(value = "/{}id")
    public ResponseEntity<Object> addCarpartsToCarinspection(@PathVariable long id,
                                                             @RequestBody Carpart carpart,
                                                             int amount){
        carinspectionService.addCarpartToOrderline(id, carpart, amount);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
