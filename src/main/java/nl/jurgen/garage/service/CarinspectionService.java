package nl.jurgen.garage.service;

import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.repository.CarinspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarinspectionService {

    @Autowired
    CarinspectionRepository carinspectionRepository;

    public List<Carinspection> getAllInspections() {
        return null;
    }
}
