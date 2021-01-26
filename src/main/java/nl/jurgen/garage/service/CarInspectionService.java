package nl.jurgen.garage.service;

import nl.jurgen.garage.model.CarInspection;
import nl.jurgen.garage.repository.CarInspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarInspectionService {

    @Autowired
    CarInspectionRepository carInspectionRepository;

    public List<CarInspection> getAllInspections() {
        return null;
    }
}
