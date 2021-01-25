package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.DatabaseErrorException;
import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.CarPart;
import nl.jurgen.garage.repository.CarPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarPartService {

    @Autowired
    CarPartRepository carPartRepository;

    public List<CarPart> getAllCarParts() {
        return carPartRepository.findAll();
    }

    public CarPart getCarPartById(long id) {

        if (carPartRepository.existsById(id)) {
            return carPartRepository.findById(id).orElse(null);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void updateStockAmount(long id, int amount) {
        if (carPartRepository.existsById(id) && (amount >= 0)) {
            try {
                CarPart carPartWithStockChange = carPartRepository.findById(id).orElse(null);
                carPartWithStockChange.setStockAmount(amount);
                carPartRepository.save(carPartWithStockChange);
            } catch (Exception e) {
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }


    public Long saveCarpart(CarPart carPart) {

        return carPartRepository.save(carPart).getId();
    }

    public void deleteCarpart(long id) {
        if (carPartRepository.existsById(id)) {
            carPartRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public CarPart updateCarpartById(long id, CarPart carPart) {
        if (carPartRepository.existsById(id)) {
            try {
                CarPart existingCarPart = carPartRepository.findById(id).orElse(null);
                existingCarPart.setStockAmount(carPart.getStockAmount());
                existingCarPart.setDescription(carPart.getDescription());
                existingCarPart.setPrice(carPart.getPrice());
                carPartRepository.save(existingCarPart);

                return existingCarPart;
            } catch (Exception e) {
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }
}