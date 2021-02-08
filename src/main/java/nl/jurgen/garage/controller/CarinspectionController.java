package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.model.EStatus;
import nl.jurgen.garage.payload.request.OrderlineCustomRequest;
import nl.jurgen.garage.service.CalculationService;
import nl.jurgen.garage.service.CarinspectionService;
import nl.jurgen.garage.service.ClientService;
import nl.jurgen.garage.service.StatusService;
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

    @Autowired
    private StatusService statusService;

    // List available inspections
    @GetMapping(value = "/list")
    public ResponseEntity<Object> getAllInspections(){
        List<Carinspection> carinspectionList = carinspectionService.getAllInspections();
        return new ResponseEntity<>(carinspectionList, HttpStatus.OK);
    }

    // Get an appointment by ID
    @GetMapping(value = "/appointmentid/{id}")
    public ResponseEntity<Object> getInspectionById(@PathVariable long id){

        Carinspection carinspection = carinspectionService.getCarinspectionById(id);
        return new ResponseEntity<>(carinspection, HttpStatus.OK);
    }
    // Add a carpart to an inspection icluding amount
    @PostMapping(value = "/carinspectionid/{carinspectionId}/carpart/{carpartId}/amount/{amount}")
    public ResponseEntity<Object> addCarpartsToCarinspection(@PathVariable long carinspectionId,
                                                             @PathVariable long carpartId,
                                                             @PathVariable int amount){

        carinspectionService.addCarpartToOrderline(carinspectionId, carpartId, amount);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Add a repair activity to a carinspection
    @PostMapping(value = "/carinspectionid/{carinspectionId}/repairactivity/{repairactivityId}/amount/{amount}")
    public ResponseEntity<Object> addRepairActivityToCarinspection(@PathVariable long carinspectionId,
                                                                   @PathVariable long repairactivityId,
                                                                   @PathVariable int amount){

        carinspectionService.addRepairActivityToCarinspection(carinspectionId, repairactivityId, amount);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // add a custom activity to a carinspection
    @PostMapping(value = "/carinspectionid/{carinspectionId}/custom")
    public ResponseEntity<Object> addCustomActivityToOrderline(@PathVariable long carinspectionId,
                                                               @RequestBody OrderlineCustomRequest orderlineCustomRequest){

        carinspectionService.addCustomActivityToOrderline(carinspectionId, orderlineCustomRequest);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get client details with a carinspection
    @GetMapping(value = "/clientdetails/{carinspectionId}")
    public ResponseEntity<Object> getClientDetailsByCarinspectionId(@PathVariable long carinspectionId){

        Client client = carinspectionService.getClientByCarinspectionId(carinspectionId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    // Get price for the repair after the carinspection and status to inspected
    @GetMapping(value = "/repairprice/{carinspectionid}")
    public ResponseEntity<Object> getPriceForRepairByCarinspection(@PathVariable long carinspectionid){

        double price = carinspectionService.getPriceForRepairByCarinspection(carinspectionid);
        statusService.changeStatus(carinspectionid, EStatus.INSPECTED);

        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    // Delete all orderlines and add carinspection costs also change carinspection status to decline_repair
    @PostMapping(value = "/declinerepair/{carinspectionid}")
    public ResponseEntity<Object> declineRepairByCarinspectionId(@PathVariable long carinspectionid){

        double price = carinspectionService.declineRepair(carinspectionid);
        statusService.changeStatus(carinspectionid, EStatus.REPAIR_DECLINED);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    // Repair car, Carinspection status to repaired
    @PostMapping(value = "/repaircar/carinspectionid/{carinspectionId}")
    public ResponseEntity<Object> repairCarAndSetStatusToRepaired(@PathVariable long carinspectionId){

    statusService.changeStatus(carinspectionId, EStatus.REPAIRED);
    return new ResponseEntity<>(HttpStatus.OK);
    }
}
