package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.payload.request.OrderlineCustomRequest;
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

    @PostMapping(value = "/{carinspectionId}/carpart/{carpartId}/amount/{amount}")
    public ResponseEntity<Object> addCarpartsToCarinspection(@PathVariable long carinspectionId,
                                                             @PathVariable long carpartId,
                                                             @PathVariable int amount){

        carinspectionService.addCarpartToOrderline(carinspectionId, carpartId, amount);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{carinspectionId}/custom")
    public ResponseEntity<Object> addCustomActivityToOrderline(@PathVariable long carinspectionId,
                                                               @RequestBody OrderlineCustomRequest orderlineCustomRequest){

        carinspectionService.addCustomActivityToOrderline(carinspectionId, orderlineCustomRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
