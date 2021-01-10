package nl.jurgen.garage.service;

import nl.jurgen.garage.model.Car;
import nl.jurgen.garage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    // Todo
    // Verwijderen van deze Service

    @Autowired
    CarRepository carRepository;
    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}
