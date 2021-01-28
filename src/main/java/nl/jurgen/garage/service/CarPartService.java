package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.DatabaseErrorException;
import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.Carpart;
import nl.jurgen.garage.repository.CarPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarPartService {

    @Autowired
    CarPartRepository carPartRepository;

    public List<Carpart> getAllCarParts() {
        return carPartRepository.findAll();
    }

    public Carpart getCarPartById(long id) {

        if (carPartRepository.existsById(id)) {
            return carPartRepository.findById(id).orElse(null);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void updateStockAmount(long id, int amount) {
        if (carPartRepository.existsById(id) && (amount >= 0)) {
            try {
                Carpart carpartWithStockChange = carPartRepository.findById(id).orElse(null);
                carpartWithStockChange.setStockAmount(amount);
                carPartRepository.save(carpartWithStockChange);
            } catch (Exception e) {
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }


    public Long saveCarpart(Carpart carPart) {

        return carPartRepository.save(carPart).getId();
    }

    public void deleteCarpart(long id) {
        if (carPartRepository.existsById(id)) {
            carPartRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public Carpart updateCarpartById(long id, Carpart carPart) {
        if (carPartRepository.existsById(id)) {
            try {
                Carpart existingCarpart = carPartRepository.findById(id).orElse(null);
                existingCarpart.setStockAmount(carPart.getStockAmount());
                existingCarpart.setDescription(carPart.getDescription());
                existingCarpart.setPrice(carPart.getPrice());
                carPartRepository.save(existingCarpart);

                return existingCarpart;
            } catch (Exception e) {
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }
}