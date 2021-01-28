package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.CarInspection;
import nl.jurgen.garage.service.CarInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/inspections")
public class CarInspectionController {

    @Autowired
    private CarInspectionService carInspectionService;

    // List available inspections
    @GetMapping(value = "/list")
    public ResponseEntity<Object> getAllInspections(){
        List<CarInspection> carInspectionList = carInspectionService.getAllInspections();
        return new ResponseEntity<>(carInspectionList, HttpStatus.OK);
    }
}