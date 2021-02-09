package nl.jurgen.garage.controller;

import nl.jurgen.garage.model.Carpart;
import nl.jurgen.garage.model.RepairActivity;
import nl.jurgen.garage.service.CarpartService;
import nl.jurgen.garage.service.RepairActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class BackOfficeController {

    @Autowired
    CarpartService carPartService;

    @Autowired
    RepairActivityService repairActivityService;

    //get a listing of all the carparts
    @GetMapping(value = "/carparts/list")
    public ResponseEntity<Object> getAllCarParts(){
        List<Carpart> carparts = carPartService.getAllCarParts();
        return new ResponseEntity<>(carparts, HttpStatus.OK);
    }

    //get a carpart by id
    @GetMapping(value = "/carparts/{id}")
    public ResponseEntity<Object> getcarPartById(@PathVariable("id") long id){
        Carpart carPart =  carPartService.getCarPartById(id);
        return new ResponseEntity<>(carPart, HttpStatus.OK);
    }

    //update CarpartStockAmount
    @PutMapping(value = "/carparts/updatestock/{id}/{amount}")
    public ResponseEntity<Object> updateStockAmountById(@PathVariable("id") int id, @PathVariable("amount") int amount){
        carPartService.updateStockAmount(id, amount);
        return new ResponseEntity<>("Voorraad mutatie succesvol doorgevoerd" ,HttpStatus.OK);
    }

    //update carpart by id and body
    @PutMapping(value = "/carparts/update/{id}")
    public ResponseEntity<Object> updateCarpartById(@PathVariable("id") int id, @RequestBody Carpart carPart){
        Carpart savedCarpart = carPartService.updateCarpartById(id, carPart);
        return new ResponseEntity<>(savedCarpart,HttpStatus.OK);
    }

    //add a new carpart
    @PostMapping(value = "/carparts/")
    public ResponseEntity<Object> saveCarpart(@RequestBody Carpart carPart){
        Long newId = carPartService.saveCarpart(carPart);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    //delete Carpart
    @DeleteMapping(value = "/carparts/delete/{id}")
    public ResponseEntity<Object> deleteCarpart(@PathVariable("id") long id){
        carPartService.deleteCarpart(id);
        return new ResponseEntity<>("Carpart with ID: " + id + " deleted", HttpStatus.OK);
    }

    // Get a list of all repair activities
    @GetMapping(value = "/repairactivity/list")
    public ResponseEntity<Object> getAllRepairActivities(){
        List<RepairActivity> repairActivityList = repairActivityService.getAllRepairActivities();
        return new ResponseEntity<>(repairActivityList, HttpStatus.OK);
    }

    // Get a repair activity by id
    @GetMapping(value = "/repairactivity/{id}")
    public ResponseEntity<Object> getRepairActivityById(@PathVariable("id") long id){
        RepairActivity repairActivity =  repairActivityService.getRepairActivityById(id);
        return new ResponseEntity<>(repairActivity, HttpStatus.OK);
    }

    // Add a repair activity
    @PostMapping(value = "/repairactivity/")
    public ResponseEntity<Object> saveRepairActivity(@RequestBody RepairActivity repairActivity){
        Long newId = repairActivityService.saveRepairActivity(repairActivity);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    // Update repair activity by id and body
    @PutMapping(value = "/repairactivity/update/{id}")
    public ResponseEntity<Object> updateRepairActivityById(@PathVariable("id") int id, @RequestBody RepairActivity repairActivity){
        RepairActivity savedRepairActivity = repairActivityService.updateRepairActivityById(id, repairActivity);
        return new ResponseEntity<>(savedRepairActivity,HttpStatus.OK);
    }

    // delete a repair activity
    @DeleteMapping(value = "/repairactivity/delete/{id}")
    public ResponseEntity<Object> deleteRepairActivity(@PathVariable("id") long id){
        repairActivityService.deleteRepairActivity(id);
        return new ResponseEntity<>("Repairactivity with ID: " + id + " deleted", HttpStatus.OK);
    }
}
