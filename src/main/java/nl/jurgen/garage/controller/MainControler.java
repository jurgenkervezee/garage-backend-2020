package nl.jurgen.garage.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainControler {

    @GetMapping(value = "/")
    public ResponseEntity<Object> sayHello(){
        return new ResponseEntity<>("Garage Api's", HttpStatus.OK);
    }

}
