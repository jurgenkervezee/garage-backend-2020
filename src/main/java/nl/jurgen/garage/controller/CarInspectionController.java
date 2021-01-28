package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.service.CarinspectionService;
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
    private CarinspectionService carinspectionService;

    // List available inspections
    @GetMapping(value = "/list")
    public ResponseEntity<Object> getAllInspections(){
        List<Carinspection> carinspectionList = carinspectionService.getAllInspections();
        return new ResponseEntity<>(carinspectionList, HttpStatus.OK);
    }
}
