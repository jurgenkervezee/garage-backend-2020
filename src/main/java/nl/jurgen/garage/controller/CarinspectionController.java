package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.payload.request.OrderlineCustomRequest;
import nl.jurgen.garage.service.CarinspectionService;
import nl.jurgen.garage.service.ClientService;
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

    @Autowired
    private ClientService clientService;

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
    // Add a carpart to an inspection icluding amount
    @PostMapping(value = "/{carinspectionId}/carpart/{carpartId}/amount/{amount}")
    public ResponseEntity<Object> addCarpartsToCarinspection(@PathVariable long carinspectionId,
                                                             @PathVariable long carpartId,
                                                             @PathVariable int amount){

        carinspectionService.addCarpartToOrderline(carinspectionId, carpartId, amount);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // add a custom activity to a carinspection
    @PostMapping(value = "/{carinspectionId}/custom")
    public ResponseEntity<Object> addCustomActivityToOrderline(@PathVariable long carinspectionId,
                                                               @RequestBody OrderlineCustomRequest orderlineCustomRequest){

        carinspectionService.addCustomActivityToOrderline(carinspectionId, orderlineCustomRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Get client details with a carinspection
    @GetMapping(value = "/clientdetails/{carinspectionId}")
    public ResponseEntity<Object> getClientDetailsByCarinspectionId(@PathVariable long carinspectionId){

        Client client = carinspectionService.getClientByCarinspectionId(carinspectionId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    // Get price for the repair after the carinspection
    @GetMapping(value = "/repairprice/{carinspectionid}")
    public ResponseEntity<Object> getPriceForRepairByCarinspection(@PathVariable long carinspectionid){

        Double price = carinspectionService.getPriceForRepairByCarinspection(carinspectionid);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @PostMapping(value = "/declinerepair/{carinspectionid}")
    public ResponseEntity<Object> declineRepairByCarinspectionId(@PathVariable long carinspectionid){

        double price = carinspectionService.declineRepair(carinspectionid);

        return new ResponseEntity<>(price, HttpStatus.OK);
    }



}
