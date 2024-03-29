package nl.jurgen.garage.controller;


import nl.jurgen.garage.exception.DuplicateRecordInDatabase;
import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.exception.StatusErrorException;
import nl.jurgen.garage.payload.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestController
@CrossOrigin("http://localhost:3000")
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DuplicateRecordInDatabase.class)
    public ResponseEntity<Object> exception(DuplicateRecordInDatabase exception){
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
    }

    @ExceptionHandler(value = StatusErrorException.class)
    public ResponseEntity<Object> exception(StatusErrorException exception){
        return new ResponseEntity<>("Carinspection has the wrong status for that action", HttpStatus.CONFLICT);
    }
}
